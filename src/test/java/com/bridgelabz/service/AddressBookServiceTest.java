package com.bridgelabz.service;

import com.bridgelabz.builder.AddressBookBuilder;
import com.bridgelabz.dto.AddressBookDTO;
import com.bridgelabz.dto.ResponseDTO;
import com.bridgelabz.entity.AddressBook;
import com.bridgelabz.exception.custom.AddressBookNotFoundException;
import com.bridgelabz.exception.custom.BadRequestException;
import com.bridgelabz.repository.AddressBookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddressBookServiceTest {

    @InjectMocks
    private AddressBookService addressBookService;

    @Mock
    private AddressBookRepository addressBookRepository;

    @Mock
    private AddressBookBuilder addressBookBuilder;

    @Test
    void whenGetAddressDetailsCalled_ShouldReturnTheListOfAddressBook() {
        List<AddressBook> addressBookList = new ArrayList<>();

        AddressBook addressBook1 = new AddressBook();
        addressBook1.setFirstName("Sudip");
        addressBook1.setLastName("Panja");
        addressBook1.setAddress("1/A/7,Ramlal AgarWala Lane");
        addressBook1.setCity("Kolkata");
        addressBook1.setState("West Bengal");
        addressBook1.setPhoneNumber("91 8910211371");
        addressBook1.setZipCode("700050");
        addressBookList.add(addressBook1);

        AddressBook addressBook2 = new AddressBook();
        addressBook2.setFirstName("Naruto");
        addressBook2.setLastName("Uzumaki");
        addressBook2.setAddress("Uzumaki Clan");
        addressBook2.setCity("Hidden Leaf Village");
        addressBook2.setState("Konohagakure");
        addressBook2.setPhoneNumber("91 8334003795");
        addressBook2.setZipCode("100026");
        addressBookList.add(addressBook2);

        when(addressBookRepository.findAll()).thenReturn(addressBookList);
        List<AddressBook> actualListOfAddressBook = addressBookService.getAddressDetails();
        assertEquals(2, actualListOfAddressBook.size());
        assertEquals(addressBookList, actualListOfAddressBook);
    }

    @Test
    void WhenFindAddressDetailsByIdCalled_ThenIfIdIsNotFound_ShouldThrowException() {
        int addressId = 1;
        when(addressBookRepository.findById(addressId)).thenReturn(Optional.empty());
        assertThrows(AddressBookNotFoundException.class, () -> addressBookService.findAddressDetailsById(addressId));
    }

    @Test
    void whenAddAddressBookCalled_ShouldAddAddressBookAndReturnResponseAndGenerateSuccessMessage() {
        AddressBook addressBook = new AddressBook();

        AddressBookDTO addressBookDTO = new AddressBookDTO();
        addressBookDTO.setFirstName("Naruto");
        addressBookDTO.setLastName("Uzumaki");
        addressBookDTO.setAddress("Uzumaki Clan");
        addressBookDTO.setCity("Hidden Leaf Village");
        addressBookDTO.setState("Konohagakure");
        addressBookDTO.setPhoneNumber("91 8334003795");
        addressBookDTO.setZipCode("100026");

        when(addressBookBuilder.buildAddressBookEntity(addressBookDTO, addressBook)).thenReturn(addressBook);
        ResponseDTO actualResponse = addressBookService.addAddressBook(addressBookDTO);
        ResponseDTO expectedResponse = new ResponseDTO(HttpStatus.CREATED,
                "Address book is added successfully!!!");

        assertEquals(expectedResponse, actualResponse);
        verify(addressBookRepository, times(1)).save(addressBook);
    }

    @Test
    void whenAddAddressBookCalled_ThenIfAddressBookDetailsAreNull_ShouldThrowException() {
        AddressBook addressBook = new AddressBook();

        AddressBookDTO addressBookDTO = null;
        assertThrows(BadRequestException.class, () -> addressBookService.addAddressBook(addressBookDTO));
        verify(addressBookRepository, times(0)).save(addressBook);
    }

    @Test
    void whenUpdateAddressBookByIdCalled_ShouldUpdateAddressBookAndReturnResponseAndGenerateSuccessMessage() {
        int addressId = 1;
        AddressBookDTO addressBookDTO = new AddressBookDTO();
        addressBookDTO.setFirstName("Sudip");
        addressBookDTO.setLastName("Panja");
        addressBookDTO.setAddress("1/A/7,Ramlal AgarWala Lane");
        addressBookDTO.setCity("Kolkata");
        addressBookDTO.setState("West Bengal");
        addressBookDTO.setPhoneNumber("91 8910211371");
        addressBookDTO.setZipCode("700050");

        AddressBook addressBook = new AddressBook();
        addressBook.setFirstName("Sudip");
        addressBook.setLastName("Panja");
        addressBook.setAddress("1/A/7,Ramlal AgarWala Lane");
        addressBook.setCity("Lake Town");
        addressBook.setState("West Bengal");
        addressBook.setPhoneNumber("91 8334003795");
        addressBook.setZipCode("700056");

        when(addressBookRepository.findById(addressId)).thenReturn(Optional.of(addressBook));
        addressBook.setCity(addressBookDTO.getCity());
        addressBook.setPhoneNumber(addressBookDTO.getPhoneNumber());
        addressBook.setZipCode(addressBookDTO.getZipCode());

        when(addressBookBuilder.buildAddressBookEntity(addressBookDTO, addressBook)).thenReturn(addressBook);
        ResponseDTO actualResponse = addressBookService.updateAddressBook(addressId, addressBookDTO);
        ResponseDTO expectedResponse = new ResponseDTO(HttpStatus.ACCEPTED,
                "Address book is updated successfully!!!");
        verify(addressBookRepository, times(1)).save(addressBook);
        assertEquals(expectedResponse, actualResponse);
        assertEquals(addressBookDTO.getCity(), addressBook.getCity());
        assertEquals(addressBookDTO.getPhoneNumber(), addressBook.getPhoneNumber());
        assertEquals(addressBookDTO.getZipCode(), addressBook.getZipCode());
    }

    @Test
    void whenUpdateAddressBookByIdCalled_ThenIfFindById_ShouldThrowException() {
        int addressId = 1;

        AddressBookDTO addressBookDTO = new AddressBookDTO();
        addressBookDTO.setFirstName("Sudip");
        addressBookDTO.setLastName("Panja");
        addressBookDTO.setAddress("1/A/7,Ramlal AgarWala Lane");
        addressBookDTO.setCity("Kolkata");
        addressBookDTO.setState("West Bengal");
        addressBookDTO.setPhoneNumber("91 8910211371");
        addressBookDTO.setZipCode("700050");

        when(addressBookRepository.findById(addressId)).thenReturn(Optional.empty());
        assertThrows(AddressBookNotFoundException.class,
                () -> addressBookService.updateAddressBook(addressId, addressBookDTO));
    }

    @Test
    void whenDeleteAddressBookByIdCalled_ShouldDeleteAddressBookAndReturnResponseAndGenerateSuccessMessage() {
        AddressBook addressBook = new AddressBook();
        addressBook.setId(1);
        addressBook.setFirstName("Sudip");
        addressBook.setLastName("Panja");
        addressBook.setAddress("1/A/7,Ramlal AgarWala Lane");
        addressBook.setCity("Lake Town");
        addressBook.setState("West Bengal");
        addressBook.setPhoneNumber("91 8334003795");
        addressBook.setZipCode("700056");

        when(addressBookRepository.findById(addressBook.getId())).thenReturn(Optional.of(addressBook));
        ResponseDTO actualResponse = addressBookService.deleteAddressBookById(addressBook.getId());
        verify(addressBookRepository).deleteById(addressBook.getId());
        ResponseDTO expectedResponse = new ResponseDTO(HttpStatus.ACCEPTED,
                "Address book is successfully deleted by id : " + addressBook.getId());
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void whenDeleteAddressBookByIdCalled_ThenIfFindById_ShouldThrowException() {
        AddressBook addressBook = new AddressBook();
        addressBook.setId(1);
        addressBook.setFirstName("Sudip");
        addressBook.setLastName("Panja");
        addressBook.setAddress("1/A/7,Ramlal AgarWala Lane");
        addressBook.setCity("Lake Town");
        addressBook.setState("West Bengal");
        addressBook.setPhoneNumber("91 8334003795");
        addressBook.setZipCode("700056");

        when(addressBookRepository.findById(addressBook.getId())).thenReturn(Optional.empty());
        assertThrows(AddressBookNotFoundException.class,
                () -> addressBookService.deleteAddressBookById(addressBook.getId()));
    }
}
