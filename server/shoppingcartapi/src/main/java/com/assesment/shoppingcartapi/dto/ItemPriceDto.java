package com.assesment.shoppingcartapi.dto;

public class ItemPriceDto {

	private long Id;
	private String Name;
	private int Unit;
	private double Price;
	
	public ItemPriceDto() {
	}

	public ItemPriceDto(long id, String name, int unit, double price) {
		super();
		Id = id;
		Name = name;
		Unit = unit;
		Price = price;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getUnit() {
		return Unit;
	}

	public void setUnit(int unit) {
		Unit = unit;
	}

	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}
	
}
