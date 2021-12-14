package com.bridgelabz.controller;

import com.bridgelabz.dto.AddressBookDTO;
import com.bridgelabz.dto.ResponseDTO;
import com.bridgelabz.entity.AddressBook;
import com.bridgelabz.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Purpose : To demonstrate different HTTP methods
 *
 * @author SUDIP PANJA
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-13
 */
@RestController
@RequestMapping
public class AddressBookController {

    @Autowired
    AddressBookService addressBookService;

    /**
     * Purpose : This API(Application programming Interface) is created for registering new address book into system
     *
     * @param addressBookDTO : takes the details of employee using POJO class of address by using @RequestBody
     * @return the new response entity which is response object and Http status in that entity
     */
    @PostMapping(value = "/address")
    public ResponseEntity<ResponseDTO> addAddressBook(
            @Valid
            @RequestBody AddressBookDTO addressBookDTO) {
        return new ResponseEntity<>
                (addressBookService.addAddressBook(addressBookDTO), HttpStatus.CREATED);
    }

    /**
     * Purpose : This API(Application programming Interface) is created for getting the list of the addresses
     *
     * @return the new response entity which is holding the list and Http status in that entity
     */
    @GetMapping(value = "/addresses")
    public ResponseEntity<List<AddressBook>> getAddressDetails() {
        return new ResponseEntity<>(addressBookService.getAddressDetails(), HttpStatus.OK);
    }

    /**
     * Purpose : This API(Application programming Interface) is created for getting the particular address details
     * by using their address id
     *
     * @param addressId : takes the address id of corresponding address book
     * @return the new response entity which is holding the address details and Http status in that entity
     */
    @GetMapping(value = "/address/{addressId}")
    public ResponseEntity<AddressBook> getAddressDetailsById(
            @PathVariable int addressId
    ) {
        return new ResponseEntity<>(addressBookService.findAddressDetailsById(addressId), HttpStatus.OK);
    }

    /**
     * Purpose : This API(Application programming Interface) is created for update the address book
     * according to their respective address id
     *
     * @param addressId      : takes the address id of corresponding address book
     * @param addressBookDTO : takes the details of address using POJO class of address book by using @RequestBody
     * @return the new response entity which holds the update message and the Http status
     */
    @PutMapping(value = "/address/{addressId}")
    public ResponseEntity<ResponseDTO> updateAddressBook(
            @PathVariable int addressId,
            @Valid
            @RequestBody AddressBookDTO addressBookDTO
    ) {
        return new ResponseEntity<>
                (addressBookService.updateAddressBook(addressId, addressBookDTO), HttpStatus.ACCEPTED);
    }

    /**
     * Purpose : This API(Application programming Interface) is created for deleting the address book
     * according to their respective address id
     *
     * @param addressId : takes the address id of corresponding address book
     * @return the new response entity which is holding the deletion message and Http status
     */
    @DeleteMapping(value = "/address/{addressId}")
    public ResponseEntity<ResponseDTO> deleteAddressBook(
            @PathVariable int addressId
    ) {
        return new ResponseEntity<>(addressBookService.deleteAddressBookById(addressId), HttpStatus.ACCEPTED);
    }
}
