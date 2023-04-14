package com.wapps.homestock.webapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "BARCODE")
public class Barcode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "global_item_id")
    private Long globalItemId;

    private String value;

    private int amount;
}
