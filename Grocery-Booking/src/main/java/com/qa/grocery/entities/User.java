package com.qa.grocery.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name ="users")
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="FirstName", nullable=false, length=100)
	private String firstName;
	
	@Column(name="lastName", nullable=false, length=100)
	private String lastName;
	
	private String email;
	
	private String password;
	
	private String about;
}
