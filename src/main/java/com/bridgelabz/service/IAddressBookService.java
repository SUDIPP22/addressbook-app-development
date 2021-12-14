package com.bridgelabz.service;

import com.bridgelabz.dto.AddressBookDTO;
import com.bridgelabz.dto.ResponseDTO;
import com.bridgelabz.entity.AddressBook;

import java.util.List;

/**
 * Purpose : This is the interface of address book service by which all the process of service class will occur
 *
 * @author SUDIP PANJA
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-13
 */
public interface IAddressBookService {
    List<AddressBook> getAddressDetails();

    AddressBook findAddressDetailsById(int addressId);

    ResponseDTO addAddressBook(AddressBookDTO addressBookDTO);

    ResponseDTO updateAddressBook(int addressId, AddressBookDTO addressBookDTO);

    ResponseDTO deleteAddressBookById(int addressId);
}
