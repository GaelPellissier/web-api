package com.wapps.homestock.webapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "REALM")
public class Realm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "owner_id")
    private Long ownerId;
}
