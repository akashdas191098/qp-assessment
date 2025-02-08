package com.qa.grocery.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.grocery.constants.QueryConstants;
import com.qa.grocery.entities.GroceryItems;

@Repository
public interface GroceryItemsRepository extends JpaRepository<GroceryItems, Integer> {

	
	@Query(value = QueryConstants.FETCH_GROCERY, nativeQuery = true)
	Page<GroceryItems> findAvailableGrocries(Pageable p);
}
