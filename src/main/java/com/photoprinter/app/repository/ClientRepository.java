package com.photoprinter.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.photoprinter.app.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {

}
