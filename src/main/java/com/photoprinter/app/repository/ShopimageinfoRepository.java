package com.photoprinter.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.photoprinter.app.model.Shopimageinfo;

@Repository
public interface ShopimageinfoRepository extends JpaRepository<Shopimageinfo, String> {

}
