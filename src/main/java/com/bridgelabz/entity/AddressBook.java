package com.bridgelabz.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Purpose : To contain the entities in the database
 *
 * @author SUDIP PANJA
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-13
 */
@Entity
@Table(name = "address")
@Data
public class AddressBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID")
    private int id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Column(name = "CITY", nullable = false)
    private String city;

    @Column(name = "STATE", nullable = false)
    private String state;

    @Column(name = "PHONE_NUMBER", unique = true, nullable = false)
    private String phoneNumber;

    @Column(name = "ZIP_CODE", nullable = false)
    private String zipCode;

}
