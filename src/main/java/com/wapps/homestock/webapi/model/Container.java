package com.wapps.homestock.webapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "CONTAINER")
public class Container {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Column(name = "creation_date")
    private Date creationDate;
    //private HashMap<Integer, String> mItemList;
}
