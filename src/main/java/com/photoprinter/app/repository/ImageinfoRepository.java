package com.photoprinter.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.photoprinter.app.model.Imageinfo;

@Repository
public interface ImageinfoRepository extends JpaRepository<Imageinfo, String> {

}
