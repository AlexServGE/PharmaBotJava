package org.example.Database;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "daily_new_procurements")
@Data
@NoArgsConstructor
public class Tender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "procurement_id")
    private String tenderId;
    @Column(name = "procurement_publication_date")
    private String tenderDate;
    @Column(name = "procurement_customer")
    private String customerLegalName;
    @Column(name = "procurement_total_value")
    private String totalAmount;
    @Column(name = "procurement_object")
    private String productMnnTendered;
    @Column(name = "procurement_link")
    private String tenderLink;
    @Column(name = "pharma_category_title")
    private String medicineCategory;
    @Column(name = "procurement_federal_region")
    private String tenderFederalRegion;

    public String getStringOutputForBot(){
        return tenderId +
                "\n" + tenderDate + '\'' +
                "\n" + customerLegalName + '\'' +
                "\n" + totalAmount + '\'' +
                "\n" + productMnnTendered + '\'' +
                "\n" + tenderLink;
    }
}
