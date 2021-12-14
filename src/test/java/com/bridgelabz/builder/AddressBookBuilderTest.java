package com.bridgelabz.builder;

import com.bridgelabz.dto.AddressBookDTO;
import com.bridgelabz.entity.AddressBook;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AddressBookBuilderTest {
    @InjectMocks
    private AddressBookBuilder addressBookBuilder;

    @Test
    void buildEntityTest() {
        AddressBookDTO addressBookDTO = new AddressBookDTO();
        addressBookDTO.setFirstName("Sudip");
        addressBookDTO.setLastName("Panja");
        addressBookDTO.setAddress("1/A/7,Ramlal AgarWala Lane");
        addressBookDTO.setCity("Kolkata");
        addressBookDTO.setState("West Bengal");
        addressBookDTO.setPhoneNumber("91 8910211371");
        addressBookDTO.setZipCode("700050");
        AddressBook addressBook = new AddressBook();
        addressBook = addressBookBuilder.buildAddressBookEntity(addressBookDTO, addressBook);
        assertEquals(addressBookDTO.getFirstName(), addressBook.getFirstName());
        assertEquals(addressBookDTO.getLastName(), addressBook.getLastName());
        assertEquals(addressBookDTO.getAddress(), addressBook.getAddress());
        assertEquals(addressBookDTO.getCity(), addressBook.getCity());
        assertEquals(addressBookDTO.getState(), addressBook.getState());
        assertEquals(addressBookDTO.getPhoneNumber(), addressBook.getPhoneNumber());
        assertEquals(addressBookDTO.getZipCode(), addressBook.getZipCode());
    }
}
