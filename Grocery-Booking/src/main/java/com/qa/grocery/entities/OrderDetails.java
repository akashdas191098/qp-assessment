package com.qa.grocery.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_details")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails extends CommonEntityProperties {
	
	@Column(name="brought_count")
	private Integer broughtCount;
	
	@Column(name = "total_cost")
	private double totalCost;
	
	@JsonIgnore
    @ManyToOne(targetEntity = GroceryItems.class, cascade = {CascadeType.ALL})
    @JoinColumn(name = "grocery_id", referencedColumnName = "id")
	private GroceryItems groceryItems;
	
	@JsonIgnore
    @ManyToOne(targetEntity = Orders.class, cascade = {CascadeType.ALL})
    @JoinColumn(name = "order_id", referencedColumnName = "id")
	private Orders order;
}
