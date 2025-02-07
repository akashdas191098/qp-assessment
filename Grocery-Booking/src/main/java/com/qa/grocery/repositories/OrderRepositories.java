package com.qa.grocery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.grocery.entities.Orders;

@Repository
public interface OrderRepositories extends JpaRepository<Orders, Integer> {

}
