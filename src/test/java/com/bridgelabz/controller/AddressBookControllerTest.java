package com.bridgelabz.controller;

import com.bridgelabz.dto.AddressBookDTO;
import com.bridgelabz.dto.ResponseDTO;
import com.bridgelabz.entity.AddressBook;
import com.bridgelabz.service.AddressBookService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AddressBookControllerTest {

    @InjectMocks
    private AddressBookController addressBookController;

    @Mock
    private AddressBookService addressBookService;

    @Test
    void givenAddresses_WhenAdded_ThenShouldAddAddressBookAndGenerateSuccessResponseAndMessage() {
        ResponseEntity<ResponseDTO> successResponseMessage = new ResponseEntity<>
                ((new ResponseDTO(HttpStatus.CREATED, "Address book is added successfully!!!")),
                        HttpStatus.CREATED);
        AddressBookDTO addressBookDTO = new AddressBookDTO();
        addressBookDTO.setFirstName("Sudip");
        addressBookDTO.setLastName("Panja");
        addressBookDTO.setAddress("1/A/7,Ramlal AgarWala Lane");
        addressBookDTO.setCity("Kolkata");
        addressBookDTO.setState("West Bengal");
        addressBookDTO.setPhoneNumber("91 8910211371");
        addressBookDTO.setZipCode("700050");
        when(addressBookService.addAddressBook(addressBookDTO))
                .thenReturn(new ResponseDTO(HttpStatus.CREATED,
                        "Address book is added successfully!!!"));
        ResponseEntity<ResponseDTO> actualResponseMessage =
                addressBookController.addAddressBook(addressBookDTO);
        assertEquals(successResponseMessage, actualResponseMessage);
    }

    @Test
    void givenAddresses_WhenGetAddressDetails_ThenShouldReturnListOfAddressBook() {
        when(addressBookService.getAddressDetails())
                .thenReturn(Lists.newArrayList(new AddressBook()));
        ResponseEntity<List<AddressBook>> actualResponse =
                addressBookController.getAddressDetails();
        assertNotNull(actualResponse);
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals(1, actualResponse.getBody().size());
    }

    @Test
    void givenAddresses_WhenUpdateAddressBookById_ThenShouldUpdateAndGenerateSuccessResponseAndMessage() {
        ResponseEntity<ResponseDTO> successResponseMessage = new ResponseEntity<>
                ((new ResponseDTO(HttpStatus.ACCEPTED,
                        "Address book is updated successfully!!!")), HttpStatus.ACCEPTED);
        int addressId = 1;
        AddressBookDTO addressBookDTO = new AddressBookDTO();
        addressBookDTO.setFirstName("Sudip");
        addressBookDTO.setLastName("Panja");
        addressBookDTO.setAddress("1/A/7,Ramlal AgarWala Lane");
        addressBookDTO.setCity("Kolkata");
        addressBookDTO.setState("West Bengal");
        addressBookDTO.setPhoneNumber("91 8910211371");
        addressBookDTO.setZipCode("700050");
        when(addressBookService.updateAddressBook(addressId, addressBookDTO))
                .thenReturn(new ResponseDTO(HttpStatus.ACCEPTED,
                        "Address book is updated successfully!!!"));
        ResponseEntity<ResponseDTO> actualResponseMessage =
                addressBookController.updateAddressBook(addressId, addressBookDTO);
        assertEquals(successResponseMessage, actualResponseMessage);
    }

    @Test
    void givenAddresses_WhenDeletedAddressBookById_ThenShouldDeleteAndGenerateSuccessResponseAndMessage() {
        ResponseEntity<ResponseDTO> successResponseMessage = new ResponseEntity<>
                ((new ResponseDTO(HttpStatus.ACCEPTED,
                        "Address book is deleted successfully!!!")), HttpStatus.ACCEPTED);
        int addressId = 1;
        when(addressBookService.deleteAddressBookById(addressId))
                .thenReturn(new ResponseDTO(HttpStatus.ACCEPTED,
                        "Address book is deleted successfully!!!"));
        ResponseEntity<ResponseDTO> actualResponseMessage =
                addressBookController.deleteAddressBook(addressId);
        assertEquals(successResponseMessage, actualResponseMessage);
    }

}
