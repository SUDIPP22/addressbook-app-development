package com.bridgelabz.exception.custom;

/**
 * Purpose : This class is created for catching the bad request exception
 * which extends the AddressBookCustomException class
 *
 * @author SUDIP PANJA
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-13
 */
public class BadRequestException extends AddressBookCustomException {

    public BadRequestException(String message) {
        super(message);
    }
}
