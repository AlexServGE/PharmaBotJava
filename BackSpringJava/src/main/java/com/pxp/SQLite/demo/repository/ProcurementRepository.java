package com.pxp.SQLite.demo.repository;

import com.pxp.SQLite.demo.entity.Procurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ProcurementRepository extends JpaRepository<Procurement, Integer> {

    Procurement findById(int id);
    ArrayList<Procurement> findAll();
}
