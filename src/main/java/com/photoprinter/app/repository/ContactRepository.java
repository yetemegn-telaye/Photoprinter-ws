package com.photoprinter.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.photoprinter.app.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {

}
