package com.pxp.SQLite.demo.entity;

import javax.persistence.*;


import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "daily_new_procurements")
@Data
@NoArgsConstructor
public class Procurement {

  @Id
  private int id;
  @Column(name = "procurement_id")
  private String tenderId;
  @Column(name = "procurement_link")
  private String tenderLink;
  @Column(name = "procurement_object")
  private String productMnnTendered;
  @Column(name = "procurement_customer")
  private String customerLegalName;
  @Column(name = "procurement_federal_region")
  private String tenderFederalRegion;
  @Column(name = "procurement_publication_date")
  private String tenderDate;
  @Column(name = "procurement_total_value")
  private String totalAmount;
  @Column(name = "pharma_category_title")
  private String medicineCategory;
}
