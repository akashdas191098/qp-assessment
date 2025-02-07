package com.qa.grocery.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qa.grocery.constants.QueryConstants;
import com.qa.grocery.entities.GroceryStockInfos;

@Repository
public interface GroceryInfosRepository extends JpaRepository<GroceryStockInfos, Integer> {
	
	@Query(value = QueryConstants.STOCK_CHECK, nativeQuery = true)
	Optional<GroceryStockInfos> getStockDetail(@Param(value="id") Integer id,@Param(value="count")Integer count);
}
