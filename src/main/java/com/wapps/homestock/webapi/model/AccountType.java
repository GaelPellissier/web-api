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

    @Column(name="TYPE_NAME")
    private String typeName;
    @Column(name="MAX_OWNED_REALM_NBR")
    private int maxOwnedRealmNbr;
    @Column(name="MAX_LOCATION_NBR")
    private int maxLocationNbr;
    @Column(name="MAX_CONT_PER_LOCATION_NBR")
    private int maxContainerPerLocationNbr;
}
