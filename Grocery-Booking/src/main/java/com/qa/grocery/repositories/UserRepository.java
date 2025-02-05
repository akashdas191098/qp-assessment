package com.qa.grocery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.grocery.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
