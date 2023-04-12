package com.wapps.homestock.webapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="ACCOUNT")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_type_id")
    private Long typeId;

    @Column(name="username")
    private String userName;

    @Column(name = "enc_password")
    private String password;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "last_modification_date")
    private Date lastModificationDate;

    @Column(name = "email")
    private String email;
}
