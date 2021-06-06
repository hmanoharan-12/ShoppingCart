package com.assesment.shoppingcartapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assesment.shoppingcartapi.coreservice.ItemService;
import com.assesment.shoppingcartapi.dto.ItemPriceDto;
import com.assesment.shoppingcartapi.entity.Item;
import com.assesment.shoppingcartapi.enumerations.Measurement;

/**
 * The Endpoint of the Item API.
 * 
 * @author harrymanoharan
 *
 */
@CrossOrigin
@RestController
public class ItemController {

	@Autowired
	private ItemService itemservice;
	
	/**
	 * Returns the list of prices for all items per quantity provided.
	 * 
	 * example endpoint url: http://<host>:<port>/items/pricelist?qty=50
	 * 
	 * @param qty Requested quantity.
	 * @return List of price information.
	 */
	@RequestMapping(path = "/items/pricelist",method = RequestMethod.GET)
	public List<ItemPriceDto> getItemPriceList(@RequestParam int qty){
		return itemservice.getPriceList(qty);
	}
	
	/**
	 * Returns the price of a given item, quantity and its unit of measure.
	 * 
	 * example endpoint url: http://<host>:<port>/items/1/price?qty=1&type=CARTON
	 * 
	 * @param id Item id.
	 * @param qty Requested quantity.
	 * @param type The unit of measure (unit/carton).
	 * @return Returns the price information.
	 */
	@RequestMapping(path = "/items/{id}/price",method = RequestMethod.GET)
	public ItemPriceDto getItemPrice(@PathVariable long id, @RequestParam int qty, @RequestParam Measurement type) {
		return itemservice.getPrice(id, qty, type);
	}
	
	/**
	 * Returns a list of items.
	 * 
	 * @return Lsit of items.
	 */
	@RequestMapping(path ="/items", method = RequestMethod.GET)
	public List<Item> getItems(){
		return itemservice.getItems();
	}
	
}
