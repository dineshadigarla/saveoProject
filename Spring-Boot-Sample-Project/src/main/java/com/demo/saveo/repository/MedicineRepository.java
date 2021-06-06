package com.demo.saveo.repository;

import com.demo.saveo.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long>, JpaSpecificationExecutor<Medicine> {

   Medicine findMedicineByCUniqueCode(String cUniqueCode);

   @Query(value="Select * from medicine where c_unique_id=?1", nativeQuery = true)
    List<Medicine> findMedicineByCUniqueIdIn(List<String> uniqueIds);



}
