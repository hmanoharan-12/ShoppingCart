package com.assesment.shoppingcartapi.objectbuilder;

import com.assesment.shoppingcartapi.entity.Item;

/**
 * Class to build dummy objects of items for testing purpose.
 * 
 * @author harrymanoharan
 *
 */
public class ItemBuilder {

	public static Item buildPenguin() {
		Item item = new Item();
		item.setItemId(1);
		item.setItemName("Penguin-ears");
		item.setCartonUnit(20);
		item.setCartonPrice(175);
		
		return item;
	}
	
	public static Item buildHorseshoe() {
		Item item = new Item();
		item.setItemId(2);
		item.setItemName("Horseshoe");
		item.setCartonUnit(5);
		item.setCartonPrice(825);
		
		return item;
	}
} 
