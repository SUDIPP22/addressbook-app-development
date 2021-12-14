package com.bridgelabz.service;

import com.bridgelabz.builder.AddressBookBuilder;
import com.bridgelabz.dto.AddressBookDTO;
import com.bridgelabz.dto.ResponseDTO;
import com.bridgelabz.entity.AddressBook;
import com.bridgelabz.exception.custom.AddressBookNotFoundException;
import com.bridgelabz.exception.custom.BadRequestException;
import com.bridgelabz.repository.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Purpose : To demonstrate business logic which implements all the methods in controller layer
 * for Address Book Application
 *
 * @author SUDIP PANJA
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-13
 */
@Service
public class AddressBookService implements IAddressBookService {

    private static final String ADDRESS_BOOK_DELETED_SUCCESSFULLY = "Address book is successfully deleted by id :";
    private static final String ADDRESS_BOOK_NOT_FOUND = "Address book is not found by this id :";
    private static final String ADDRESS_BOOK_ADDED_SUCCESSFULLY = "Address book is added successfully!!!";
    private static final String ADDRESS_BOOK_UPDATED_SUCCESSFULLY = "Address book is updated successfully!!!";
    @Autowired
    private AddressBookRepository addressBookRepository;

    @Autowired
    private AddressBookBuilder addressBookBuilder;

    /**
     * Purpose : This method is used to get back the list of address details
     *
     * @return the address book entity
     */
    @Override
    public List<AddressBook> getAddressDetails() {
        return addressBookRepository.findAll();
    }

    /**
     * Purpose : This method is used to get back the corresponding address details by respective address id
     *
     * @param addressId : takes the address id of that particular address entity
     * @return the address entity using the address id
     */
    @Override
    public AddressBook findAddressDetailsById(int addressId) {
        return addressBookRepository.findById(addressId)
                .orElseThrow(() -> new AddressBookNotFoundException(ADDRESS_BOOK_NOT_FOUND + " " + addressId));
    }

    /**
     * Purpose : This method is used to add the addresses by using of address book DTO class
     *
     * @param addressBookDTO : takes the addresses as DTO to provide the repository for storing in database
     * @return the new response of success message and Http status
     */
    @Override
    public ResponseDTO addAddressBook(AddressBookDTO addressBookDTO) {
        if (addressBookDTO == null) {
            throw new BadRequestException("Addresses are null");
        }
        AddressBook addressBook = new AddressBook();
        addressBook = addressBookBuilder.buildAddressBookEntity(addressBookDTO, addressBook);
        addressBookRepository.save(addressBook);
        return new ResponseDTO(HttpStatus.CREATED, ADDRESS_BOOK_ADDED_SUCCESSFULLY);
    }

    /**
     * Purpose : This method is used to update the address book by using their respective address id
     *
     * @param addressId      : takes the address id for updating that particular address entity
     * @param addressBookDTO : takes the updated address book as DTO
     *                       to provide the repository for storing in database
     * @return the new response of success message and Http status
     */
    @Override
    public ResponseDTO updateAddressBook(int addressId, AddressBookDTO addressBookDTO) {
        AddressBook addressBook = findAddressDetailsById(addressId);
        addressBook = addressBookBuilder.buildAddressBookEntity(addressBookDTO, addressBook);
        addressBookRepository.save(addressBook);
        return new ResponseDTO(HttpStatus.ACCEPTED, ADDRESS_BOOK_UPDATED_SUCCESSFULLY);
    }

    /**
     * Purpose : This method is used to delete the address book by using the respective address id
     *
     * @param addressId : takes the address id for deleting that particularly addrress entity
     * @return the new response of success message and Http status
     */
    @Override
    public ResponseDTO deleteAddressBookById(int addressId) {
        AddressBook addressBook = findAddressDetailsById(addressId);
        addressBookRepository.deleteById(addressId);
        return new ResponseDTO(HttpStatus.ACCEPTED, ADDRESS_BOOK_DELETED_SUCCESSFULLY + " " + addressId);
    }
}
