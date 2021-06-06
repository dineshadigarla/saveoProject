package com.demo.saveo.service.impl;


import com.demo.saveo.dto.MedicinesDto;
import com.demo.saveo.exception.BadRequestException;
import com.demo.saveo.model.Medicine;
import com.demo.saveo.model.Order;
import com.demo.saveo.repository.MedicineRepository;
import com.demo.saveo.repository.OrderRepository;
import com.demo.saveo.service.DataService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataServiceImpl implements DataService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<Medicine> insertDataFromCSV(String fileUri) throws Exception{
        BufferedReader reader = Files.newBufferedReader(Paths.get("C://Users/User/Downloads/Product list - Sheet1 copy.csv"));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader("c_name","c_batch_no","d_expiry_date","n_balance_qty","c_packaging","c_unique_code","c_schemes","n_mrp","c_manufacturer","hsn_code").withIgnoreHeaderCase().withTrim());
        List<Medicine> medicines = new ArrayList<>();
        int i=0;
        for (CSVRecord csvRecord: csvParser) {
            if(i==0){
                i++;
                continue;
            }

            // Accessing Values by Column Index
            Medicine medicine = new Medicine();
            medicine.setCManufacturer(csvRecord.get("c_manufacturer"));
            medicine.setCBatchNo(csvRecord.get("c_batch_no"));
            medicine.setCName(csvRecord.get("c_name"));
            medicine.setHsnCode(csvRecord.get("hsn_code"));
            medicine.setCPackaging(csvRecord.get("c_packaging"));
            medicine.setDExpiryDate(csvRecord.get("d_expiry_date"));
            medicine.setNMrp(csvRecord.get("n_mrp"));
            medicine.setNBalanceQty(csvRecord.get("n_balance_qty"));
            medicine.setCSchemes(csvRecord.get("c_schemes"));
            medicine.setCUniqueCode(csvRecord.get("c_unique_code"));
            medicines.add(medicine);
            //Accessing the values by column header name

            System.out.print(medicine.toString());
            System.out.println("\n\n");
        }
        return medicineRepository.saveAll(medicines);

    }

    public List<Medicine> searchMedicines(String name){

        System.out.println(name);
        System.out.println(getLikeSpecification("c_name", name));

        return medicineRepository.findAll(getLikeSpecification("c_name", name));
    }

    private <T> Specification<T> getLikeSpecification(String key, String value) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                return builder.like(root.get(key).as(String.class), value);
            }};
    }

    public Medicine getMedicineById(String medicineId){

        return medicineRepository.findMedicineByCUniqueCode(medicineId);
    }

    public Long placeOrder(MedicinesDto medicines){

        List<String> uniqueIds = medicines.getMedicines().stream().map(medicine -> medicine.getCUniqueCode()).collect(Collectors.toList());
        List<Medicine> medicinesInDb = medicineRepository.findMedicineByCUniqueIdIn(uniqueIds);
        Order order = new Order();
        if(!CollectionUtils.isEmpty(medicinesInDb) && medicinesInDb.size()==medicines.getMedicines().size()){
            order.setMedicines(new HashSet<>(medicinesInDb));
            order = orderRepository.save(order);
            return order.getId();
        }
        else{
            throw new BadRequestException("The medicine details are invalid");
        }
    }


}
