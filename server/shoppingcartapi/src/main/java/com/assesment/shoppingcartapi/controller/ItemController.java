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

@CrossOrigin
@RestController
public class ItemController {

	@Autowired
	private ItemService itemservice;
	
	@RequestMapping(path = "/items/pricelist",method = RequestMethod.GET)
	public List<ItemPriceDto> getItemPriceList(@RequestParam int qty){
		return itemservice.getPriceList(qty);
	}
	
	@RequestMapping(path = "/items/{id}/price",method = RequestMethod.GET)
	public ItemPriceDto getItemPrice(@PathVariable long id, @RequestParam int qty, @RequestParam Measurement type) {
		return itemservice.getPrice(id, qty, type);
	}
	
	@RequestMapping(path ="/items", method = RequestMethod.GET)
	public List<Item> getItems(){
		return itemservice.getItems();
	}
	
}
