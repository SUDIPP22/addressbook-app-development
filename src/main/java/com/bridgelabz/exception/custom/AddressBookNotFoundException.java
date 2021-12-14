package com.bridgelabz.exception.custom;

/**
 * Purpose : This class is created for generating catching the address book not found exception
 * which extends the AddressBookCustomException class
 *
 * @author SUDIP PANJA
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-13
 */
public class AddressBookNotFoundException extends AddressBookCustomException {

    public AddressBookNotFoundException(String message) {
        super(message);
    }
}
