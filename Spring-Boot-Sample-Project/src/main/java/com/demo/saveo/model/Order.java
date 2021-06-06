package com.demo.saveo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="order")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(mappedBy = "orders")
    private Set<Medicine> medicines = new HashSet<>();
}
