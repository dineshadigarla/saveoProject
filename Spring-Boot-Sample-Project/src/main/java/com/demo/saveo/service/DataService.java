package com.demo.saveo.service;

import com.demo.saveo.dto.MedicinesDto;
import com.demo.saveo.model.Medicine;

import java.util.List;

public interface DataService {

    public List<Medicine> insertDataFromCSV(String fileUri) throws Exception;

    public List<Medicine> searchMedicines(String name);

    public Medicine getMedicineById(String medicineId);

    Long placeOrder(MedicinesDto medicines);
}
