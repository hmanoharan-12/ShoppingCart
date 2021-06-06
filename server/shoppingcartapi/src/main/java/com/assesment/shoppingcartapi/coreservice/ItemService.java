package com.assesment.shoppingcartapi.coreservice;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assesment.shoppingcartapi.dao.ItemDao;
import com.assesment.shoppingcartapi.dto.ItemPriceDto;
import com.assesment.shoppingcartapi.entity.Item;
import com.assesment.shoppingcartapi.enumerations.Measurement;

/**
 * The core service layer of the api.
 * 
 * @author harrymanoharan
 *
 */
@Service
public class ItemService {

	
	@Autowired
	private ItemDao itemDao;
	
    @Autowired
    public ItemService(ItemDao itemDao) {
        this.itemDao = itemDao;
    }
	
	/**
	 * Returns the price from 1-requested quantity of items.
	 * 
 	 * @param qty The requested quantity
	 * @return List of prices.
	 */
	public List<ItemPriceDto> getPriceList(int qty) {
		
		List<Item> items = itemDao.findAll();
		
		List<ItemPriceDto> itemPriceDto= new ArrayList<ItemPriceDto>();
		
		for (Item item : items) {
			for (int i = 1; i <= qty; i++ ) {
				double itemPrice;
				itemPrice = calculatePrice(item.getCartonUnit(),item.getCartonPrice(),i);
				itemPriceDto.add(new ItemPriceDto(item.getItemId(),item.getItemName(),i,itemPrice));
			}
		}
		
		return itemPriceDto;	
	}
	
	/**
	 * Returns the price information of the requested quantity of item. 
	 * 
	 * @param id The Item Id.
	 * @param qty The request quantity.
	 * @param type Requested quantity by CARTON or UNIT
	 * @return The price object.
	 */
	public ItemPriceDto getPrice(long id , int qty, Measurement type) {
		
		Item item = itemDao.findById(id);
		double price;
		
		if(type.equals(Measurement.CARTON)) {
			price = calculatePrice(item.getCartonUnit(), item.getCartonPrice(), qty * item.getCartonUnit());
		}else {
			price = calculatePrice(item.getCartonUnit(), item.getCartonPrice(), qty);
		}
		
		return (new ItemPriceDto(id, item.getItemName(), qty, price));
		
	}
	
	/**
	 * Calculates and returns the price of a given quantity of item.
	 * 
	 * @param cartonUnit The number of units in a carton of item. 
	 * @param cartonPrice The price of the carton.
	 * @param qty The requested quantity.
	 * @return price of the requested quantity.
	 */
	private double calculatePrice(int cartonUnit , double cartonPrice, int qty) {
		
		int noOfCartons = qty / cartonUnit; //Find the number of cartons from the quantity requested.
		int invUnits = qty % cartonUnit; //Find the number of individual units.
		double pricePerUnit = cartonPrice / cartonUnit; //Find the standard price per unit.
		
		if(noOfCartons >= 3) {
			return Math.round(((noOfCartons * cartonPrice * 0.9) + (invUnits * 1.3 * pricePerUnit)));
		}else if(noOfCartons == 0) {
			return Math.round( (qty * pricePerUnit * 1.3));
		}else {
			return Math.round(((noOfCartons * cartonPrice) + invUnits * 1.3 *  pricePerUnit));
		}
	}
	
	/**
	 * Return all Items.
	 * 
	 * @return List of Items.
	 */
	public List<Item> getItems(){
		return itemDao.findAll();
	}
	
}
