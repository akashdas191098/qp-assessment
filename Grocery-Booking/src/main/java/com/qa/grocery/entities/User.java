package com.qa.grocery.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends CommonEntityProperties {

	@Column(name="firstName", nullable=false, length=100)
	private String firstName;
	
	@Column(name="lastName", nullable=false, length=100)
	private String lastName;
	
	private String email;
	
	private String password;
	
	private boolean isAdmin;
	
//	@Column(name="broughtCount")
//	private Integer broughtCount;
	
//    @JsonIgnore
//    @OneToMany(targetEntity = GroceryItems.class, cascade = { CascadeType.ALL })
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private List<GroceryItems> groceryId;
}
