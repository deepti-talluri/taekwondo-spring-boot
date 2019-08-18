package com.example.database.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "INVENTORY")
@Getter
@Setter
public class Inventory {
    @Id
    @GeneratedValue(generator = "INVENTORY_SEQUENCE")
    @SequenceGenerator(sequenceName = "INVENTORY_SEQUENCE", name = "INVENTORY_SEQUENCE")
    @Column(name = "INVENTORY_ID")
    private Long id;

    @Basic
    @Column(name = "INVENTORY_NAME", nullable = false)
    private String name;

    @Basic
    @Column(name = "DESCRIPTION")
    private String description;

    @Basic
    @Column(name = "COST_PRICE", nullable = false)
    private Double costPrice;

    @Basic
    @Column(name = "SELLING_PRICE", nullable = false)
    private Double sellingPrice;

    @Basic
    @Column(name = "COUNT", nullable = false)
    private Integer count;

}
