package com.bridgelabz.exception.custom;

/**
 * Purpose : This class is created for catching the address book custom exception
 * which extends the runtime exception
 *
 * @author SUDIP PANJA
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-13
 */

public class AddressBookCustomException extends RuntimeException {

    public AddressBookCustomException(String message) {
        super(message);
    }
}
