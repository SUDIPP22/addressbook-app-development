package com.bridgelabz.builder;

import com.bridgelabz.dto.AddressBookDTO;
import com.bridgelabz.entity.AddressBook;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * Purpose : This is builder class which holds all the building related application
 *
 * @author SUDIP PANJA
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-13
 */
@Component
public class AddressBookBuilder {

    /**
     * Purpose : This method is created to copy the properties of simple POJO to @Entity class
     *
     * @param addressBookDTO : This the POJO class's object which is the source for copying the properties
     * @param addressBook    : This is the Entity class's object which is the destination for that copied properties
     * @return the object of AddressBook class
     */
    public AddressBook buildAddressBookEntity(AddressBookDTO addressBookDTO, AddressBook addressBook) {
        BeanUtils.copyProperties(addressBookDTO, addressBook);
        return addressBook;
    }
}
