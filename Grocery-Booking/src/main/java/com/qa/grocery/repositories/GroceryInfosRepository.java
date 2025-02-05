package com.qa.grocery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.grocery.entities.GroceryStockInfos;

@Repository
public interface GroceryInfosRepository extends JpaRepository<GroceryStockInfos, Integer> {

}
