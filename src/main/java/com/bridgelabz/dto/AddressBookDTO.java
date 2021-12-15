package com.bridgelabz.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
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

    @NotNull(message = "First name should not be empty")
    @Pattern(regexp = "^[A-Z][a-z]{2,}$", message = "First name is invalid!!!")
    private String firstName;

    @NotNull(message = "Last name should not be empty")
    @Pattern(regexp = "^[A-Z][a-z]{2,}$", message = "Last name is invalid!!!")
    private String lastName;

    @NotNull(message = "Address should not be empty")
    private String address;

    @NotNull(message = "City name should not be empty")
    private String city;

    @NotNull(message = "State name should not be empty")
    private String state;

    @NotNull(message = "Phone number should not be empty")
    @Pattern(regexp = "^[91]{2}[ ][0-9]{10}$", message = "Phone number is invalid!!!")
    @Column(unique = true)
    private String phoneNumber;

    @NotNull(message = "Zip code should not be empty")
    @Size(max = 6, message = "Zipcode is invalid!!!")
    private String zipCode;
}
