package com.qa.grocery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.grocery.entities.GroceryItems;

public interface GroceryItemsRepository extends JpaRepository<GroceryItems, Integer> {

}
