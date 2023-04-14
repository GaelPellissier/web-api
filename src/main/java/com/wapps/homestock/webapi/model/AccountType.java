package com.wapps.homestock.webapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="ACCOUNT_TYPE")
public class AccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="type_name")
    private String typeName;
    @Column(name="max_owned_realm_nbr")
    private int maxOwnedRealmNbr;
    @Column(name="max_location_nbr")
    private int maxLocationNbr;
    @Column(name="max_cont_per_location_nbr")
    private int maxContainerPerLocationNbr;
}
