package com.qa.grocery.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "groceries")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroceryItems extends CommonEntityProperties {
	
	@Column(name="itemName", nullable=false, length=100)
	private String name;
	
	private double price;
	
	@Column(name="madeBy", nullable = false, length=100)
	private String mfgBy;
	
	@Column(name="bestBefore", nullable = false, length=100)
	private String expairyDate;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "stockInfo_id", referencedColumnName = "id")
	private GroceryStockInfos groceryInfos;
	
//	@JsonIgnore
//    @ManyToOne(targetEntity = User.class, cascade = {CascadeType.ALL})
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private User users;
	
}
