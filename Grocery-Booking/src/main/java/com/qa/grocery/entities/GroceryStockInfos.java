package com.qa.grocery.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Grocery_Stock_Details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroceryStockInfos extends CommonEntityProperties {
	
	private boolean inStock;
	
	@Column(name="total_item")
	private Integer stockInCount;
}
