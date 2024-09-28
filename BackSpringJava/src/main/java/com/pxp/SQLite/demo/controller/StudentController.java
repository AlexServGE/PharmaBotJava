package com.pxp.SQLite.demo.controller;

import com.pxp.SQLite.demo.entity.Procurement;

import com.pxp.SQLite.demo.service.ProcurementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("procurement")
@RequiredArgsConstructor
public class StudentController {

    private final ProcurementService service;

    @GetMapping("/{id}")
    public ResponseEntity<Procurement> getProcurement(@PathVariable int id) {
        log.info("Поступил запрос на получение информации о закупке: procurementId={}"
                , id);

        try {
            return ResponseEntity.ok().body(service.getProcurement(id));
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }
}
