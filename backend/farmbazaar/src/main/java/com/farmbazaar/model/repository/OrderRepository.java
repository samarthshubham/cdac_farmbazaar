/*
Author: Shubham Samarth
Date: February 18, 2024
Description: This interface extends JpaRepository to provide CRUD (Create, Read, Update, Delete) operations for the Order entity in the FarmBazaar application. It allows interaction with the underlying database table representing orders.
*/
package com.farmbazaar.model.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.farmbazaar.model.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}