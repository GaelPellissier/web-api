package com.wapps.homestock.webapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "LOCATION")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "realm_id")
    private Long realmId;
    private String name;
    private String description;
    @Column(name = "creation_date")
    private Date creationDate;
    @Column(name = "last_modification_date")
    private Date lastModificationDate;
    //private HashMap<Integer, String> mContainerList;
}
