package com.bridgelabz.integration;

import com.bridgelabz.controller.AddressBookController;
import com.bridgelabz.dto.AddressBookDTO;
import com.bridgelabz.dto.ResponseDTO;
import com.bridgelabz.entity.AddressBook;
import com.bridgelabz.service.AddressBookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AddressBookController.class)
@ActiveProfiles("test")
public class AddressBookControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressBookService addressBookService;

    @Test
    void addAddressBookTest() throws Exception {
        when(addressBookService.addAddressBook(any())).thenReturn(new ResponseDTO());
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/address")
                        .content("{\"firstName\":\"Sudip\",\"lastName\":\"Panja\"," +
                                "\"address\":\"1/A/7,RamlalAgarWalaLane\",\"city\":\"Kolkata\"," +
                                "\"state\":\"West Bengal\",\"phoneNumber\":\"91 8910211371\",\"zipCode\":700050}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());
    }

    @Test
    void addAddressBookTest_WhenValidationFailed_ShouldGetBadRequest() throws Exception {
        when(addressBookService.addAddressBook(any())).thenReturn(new ResponseDTO());
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/address")
                        .content("{\"firstName\":\"Sudip\",\"lastName\":\"panja\"," +
                                "\"address\":\"1/A/7,RamlalAgarWalaLane\",\"city\":\"Kolkata\"," +
                                "\"state\":\"West Bengal\",\"phoneNumber\":\"918910211371\",\"zipCode\":7000501}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getAddressDetailsTest() throws Exception {
        when(addressBookService.getAddressDetails()).thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders.get("/addresses"))
                .andExpect(status().isOk());
    }

    @Test
    void getAddressDetailsByIdTest() throws Exception {
        AddressBook addressBook = new AddressBook();
        when(addressBookService.findAddressDetailsById(1)).thenReturn(addressBook);
        mockMvc.perform(MockMvcRequestBuilders.get("/address/1"))
                .andExpect(status().isOk());
    }

    @Test
    void updateAddressBookTest() throws Exception {
        AddressBookDTO addressBookDTO = new AddressBookDTO();
        addressBookDTO.setFirstName("Naruto");
        addressBookDTO.setLastName("Uzumaki");
        addressBookDTO.setAddress("Uzumaki Clan");
        addressBookDTO.setCity("Hidden Leaf Village");
        addressBookDTO.setState("Konohagakure");
        addressBookDTO.setPhoneNumber("91 8334003795");
        addressBookDTO.setZipCode("100026");
        int addressId = 1;
        when(addressBookService.updateAddressBook(addressId, addressBookDTO)).thenReturn(new ResponseDTO());
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/address/1")
                        .content("{\"firstName\":\"Sudip\",\"lastName\":\"Panja\"," +
                                "\"address\":\"1/A/7,RamlalAgarWalaLane\",\"city\":\"Kolkata\"," +
                                "\"state\":\"West Bengal\",\"phoneNumber\":\"91 8910211371\",\"zipCode\":700050}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isAccepted());
    }

    @Test
    void updateAddressBookTest_WhenValidationFailed_ShouldGetBadRequest() throws Exception {
        AddressBookDTO addressBookDTO = new AddressBookDTO();
        addressBookDTO.setFirstName("Naruto");
        addressBookDTO.setLastName("Uzumaki");
        addressBookDTO.setAddress("Uzumaki Clan");
        addressBookDTO.setCity("Hidden Leaf Village");
        addressBookDTO.setState("Konohagakure");
        addressBookDTO.setPhoneNumber("91 8334003795");
        addressBookDTO.setZipCode("100026");
        int addressId = 1;
        when(addressBookService.updateAddressBook(addressId, addressBookDTO)).thenReturn(new ResponseDTO());
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/address/1")
                        .content("{\"firstName\":\"sudip\",\"lastName\":\"panja\"," +
                                "\"address\":\"1/A/7,RamlalAgarWalaLane\",\"city\":\"Kolkata\"," +
                                "\"state\":\"West Bengal\",\"phoneNumber\":\"91 89102113717\",\"zipCode\":7000550}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteAddressBookTest() throws Exception {
        when(addressBookService.deleteAddressBookById(1)).thenReturn(new ResponseDTO());
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/address/1")
                        .content("{\"firstName\":\"Sudip\",\"lastName\":\"Panja\"," +
                                "\"address\":\"1/A/7,RamlalAgarWalaLane\",\"city\":\"Kolkata\"," +
                                "\"state\":\"West Bengal\",\"phoneNumber\":\"91 8910211371\",\"zipCode\":700050}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isAccepted());
    }
}
