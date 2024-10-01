package com.pxp.SQLite.demo.repository;

import com.pxp.SQLite.demo.entity.Procurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ProcurementRepository extends JpaRepository<Procurement, Integer> {

  List<Procurement> findProcurementsByTenderDateBetween(String tenderDate, String tenderDate2);

  List<Procurement> findProcurementsByMedicineCategoryAndTenderFederalRegionAndTenderDateBetween(String medicineCategory, String tenderFederalRegion, String tenderDate, String tenderDate2);

}
