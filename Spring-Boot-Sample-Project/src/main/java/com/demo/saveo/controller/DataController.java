package com.demo.saveo.controller;


import com.demo.saveo.dto.FileDto;
import com.demo.saveo.dto.MedicinesDto;
import com.demo.saveo.dto.ResponseDto;
import com.demo.saveo.model.Medicine;
import com.demo.saveo.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/data", produces = { MediaType.APPLICATION_JSON_VALUE })
public class DataController {

    @Autowired
    private DataService dataService;

    @PostMapping("/upload-csv")
    public ResponseDto uploadData(@RequestBody FileDto fileDto) {
        try {
            List<Medicine> medicineList = dataService.insertDataFromCSV(fileDto.getFileUri());

            return new ResponseDto(HttpStatus.OK.value(), medicineList);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/searchMedicine")
    public ResponseDto searchMedicines(@RequestParam("name") String name){

        List<Medicine> medicineList = dataService.searchMedicines(name);
        return new ResponseDto(HttpStatus.OK.value(), medicineList);
    }

    @GetMapping("/getMedicineDetails/{medicineId}")
    public ResponseDto getMedicineById(@PathVariable("medicineId") String medicineId){

        Medicine medicine = dataService.getMedicineById(medicineId);
        return new ResponseDto(HttpStatus.OK.value(), medicine);
    }

    @PostMapping("/placeorder")
    public ResponseDto placeOrder(@RequestBody MedicinesDto medicinesDto){
        Long orderId = dataService.placeOrder(medicinesDto);

        return new ResponseDto(HttpStatus.OK.value(), orderId);
    }
}
