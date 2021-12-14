package com.bridgelabz.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Purpose : To invoke the data from client
 *
 * @author SUDIP PANJA
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-13
 */
@Data
public class AddressBookDTO {

    @Pattern(regexp = "^[A-Z][a-z]{2,}$", message = "First name is invalid!!!")
    private String firstName;

    @Pattern(regexp = "^[A-Z][a-z]{2,}$", message = "Last name is invalid!!!")
    private String lastName;

    private String address;

    private String city;

    private String state;

    @Pattern(regexp = "^[91]{2}[ ][0-9]{10}$", message = "Phone number is invalid!!!")
    @Column(unique = true)
    private String phoneNumber;

    @Size(max = 6, message = "Zipcode is invalid!!!")
    private String zipCode;
}
