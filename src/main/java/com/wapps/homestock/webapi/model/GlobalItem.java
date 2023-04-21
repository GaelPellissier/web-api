package com.wapps.homestock.webapi.model;

import com.wapps.homestock.webapi.model.container.BasePOJO;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "GLOBAL_ITEM")
public class GlobalItem extends BasePOJO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Column(name = "is_stock_low")
    private boolean isStockLow;
    private double price;
}
