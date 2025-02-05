package com.qa.grocery.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qa.grocery.constants.QueryConstants;
import com.qa.grocery.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query(value = QueryConstants.CHECK_USER_IS_ADMIN, nativeQuery = true)
	Optional<User> checkIfAdmin(@Param(value="id")Integer id);
}
