package com.wapps.homestock.webapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "CURRENCY")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trigram;

    private String name;
}
