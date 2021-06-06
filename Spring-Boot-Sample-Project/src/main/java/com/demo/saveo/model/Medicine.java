package com.demo.saveo.model;


import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "medicine")
public class Medicine {

    private @Id
    @GeneratedValue
    Long id;

    @Column(name= "c_name")
    private String cName;

    @Column(name="c_batch_no")
    private String cBatchNo;

    @Column(name = "d_expiry_date")
    private String dExpiryDate;

    @Column(name="n_balance_qty")
    private String nBalanceQty;

    @Column(name="c_packaging")
    private String cPackaging;

    @Column(name = "c_unique_code")
    private String cUniqueCode;

    @Column(name="c_schemes")
    private String cSchemes;

    @Column(name="n_mrp")
    private String nMrp;

    @Column(name="c_manufacturer")
    private String cManufacturer;

    @Column(name="hsn_code")
    private String hsnCode;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Medicine_Order",
            joinColumns = { @JoinColumn(name = "medicine_id") },
            inverseJoinColumns = { @JoinColumn(name = "order_id") }
    )
    private Set<Order> orders = new HashSet<>();
}
