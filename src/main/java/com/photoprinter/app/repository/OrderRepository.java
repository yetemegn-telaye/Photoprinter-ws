package com.photoprinter.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.photoprinter.app.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

}
