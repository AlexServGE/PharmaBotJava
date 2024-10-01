package com.pxp.SQLite.demo.service;

import com.pxp.SQLite.demo.entity.Procurement;
import com.pxp.SQLite.demo.repository.ProcurementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProcurementService {

  private final ProcurementRepository procurementRepository;

//    public Procurement getProcurement(int id) {
//        if (procurementRepository.findById(id) == null) {
//            log.info("Не удалось найти книгу с id " + id);
//            throw new NoSuchElementException("Не удалось найти книгу с id " + id);
//        }
//        return procurementRepository.findById(id);
//    }
//
//    public List<Procurement> getAllProcurements() {
//        if (procurementRepository.findAll() == null) {
//            log.info("Не удалось найти все закупки");
//            throw new NoSuchElementException("Не удалось найти все закупки");
//        }
//        return procurementRepository.findAll();
//    }

  public List<Procurement> getProcurementsByTenderDateBetween(String tenderDate1, String tenderDate2) {
    if (procurementRepository.findProcurementsByTenderDateBetween(tenderDate1, tenderDate2) == null) {
      log.info("Не удалось найти все закупки");
      throw new NoSuchElementException("Не удалось найти все закупки");
    }
    return procurementRepository.findProcurementsByTenderDateBetween(tenderDate1, tenderDate2);
  }

  public List<Procurement> getProcurementsByMedicineCategoryAndTenderFederalRegionAndTenderDateBetween(String medicineCategory, String tenderFederalRegion,String tenderDate, String tenderDate2) {
    if (procurementRepository.findProcurementsByMedicineCategoryAndTenderFederalRegionAndTenderDateBetween(medicineCategory, tenderFederalRegion,tenderDate,tenderDate2) == null) {
      log.info("Не удалось найти все закупки");
      throw new NoSuchElementException("Не удалось найти все закупки");
    }
    return procurementRepository.findProcurementsByMedicineCategoryAndTenderFederalRegionAndTenderDateBetween(medicineCategory, tenderFederalRegion,tenderDate,tenderDate2);
  }
}
