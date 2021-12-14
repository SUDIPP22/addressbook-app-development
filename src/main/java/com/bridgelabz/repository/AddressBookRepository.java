package com.bridgelabz.repository;

import com.bridgelabz.entity.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Purpose : To implement an interface which operate the database operation
 * for Address Book Application
 *
 * @author SUDIP PANJA
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-13
 */
@Repository
public interface AddressBookRepository extends JpaRepository<AddressBook, Integer> {
}
