package com.pxp.SQLite.demo.controller;

import com.pxp.SQLite.demo.entity.Procurement;

import com.pxp.SQLite.demo.service.ProcurementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import java.util.NoSuchElementException;


@Slf4j
@RestController
@RequestMapping("procurement")
@RequiredArgsConstructor
public class ProcurementController {

  private final ProcurementService service;

  @GetMapping(path = "/{date1}-{date2}", produces = "application/json")
//  @CrossOrigin(origins ="http://127.0.0.1:5000",maxAge = 3600)
  public ResponseEntity<List<Procurement>> getProcurementsByTenderDateBetween(@PathVariable String date1, @PathVariable String date2) {
    log.info("Поступил запрос на получение информации закупках на дату c - {}, по - {}", date1, date2);

    try {
      return ResponseEntity.ok().body(service.getProcurementsByTenderDateBetween(date1, date2));
    } catch (NoSuchElementException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping(path = "/{category}/{federalRegion}/{date1}-{date2}", produces = "application/json")
  //  @CrossOrigin(origins ="http://127.0.0.1:5000",maxAge = 3600)
  public ResponseEntity<List<Procurement>> getProcurementsByMedicineCategoryAndTenderFederalRegionAndTenderDateBetween(@PathVariable String category, @PathVariable String federalRegion, @PathVariable String date1, @PathVariable String date2) {
    log.info("Поступил запрос на получение информации закупках в категории - {}, в регионе - {}, на дату c - {}, по - {}", category, federalRegion, date1, date2);

    try {
      return ResponseEntity.ok().body(service.getProcurementsByMedicineCategoryAndTenderFederalRegionAndTenderDateBetween(category, federalRegion, date1, date2));
    } catch (NoSuchElementException e) {
      return ResponseEntity.notFound().build();
    }
  }

}
