package com.assesment.shoppingcartapi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * The Entity class for the Item.
 * 
 * @author harrymanoharan
 *
 */
@Entity
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long itemId;
	private String itemName;
	private int cartonUnit;
	private double cartonPrice;
	
	
	public Item() {
	}
	
	public long getItemId() {
		return itemId;
	}
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getCartonUnit() {
		return cartonUnit;
	}
	public void setCartonUnit(int cartonUnit) {
		this.cartonUnit = cartonUnit;
	}
	public double getCartonPrice() {
		return cartonPrice;
	}
	public void setCartonPrice(double cartonPrice) {
		this.cartonPrice = cartonPrice;
	}
	
	
}
