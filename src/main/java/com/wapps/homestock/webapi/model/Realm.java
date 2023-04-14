package com.wapps.homestock.webapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "REALM")
public class Realm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "currency_id")
    private Long currencyId;

    @Column(name = "max_location_number")
    private int maxLocationNumber;

    private String name;

    private String description;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "last_modification_date")
    private Date lastModificationDate;
    /*private HashMap<Integer, String> mLocationList;
    private List<GlobalItemDTO> mGlobalItemList;*/
}
