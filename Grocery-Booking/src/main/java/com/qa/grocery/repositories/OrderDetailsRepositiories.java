package com.qa.grocery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.grocery.entities.OrderDetails;

@Repository
public interface OrderDetailsRepositiories extends JpaRepository<OrderDetails, Integer> {

}
