package com.assesment.shoppingcartapi.dao;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.assesment.shoppingcartapi.entity.Item;

/**
 * Generic interface to perform CRUD operations on Item Entity.
 * 
 * @author harrymanoharan
 *
 */
public interface ItemDao extends CrudRepository<Item, Long> {

	/**
	 * Returns all items.
	 * 
	 * @return All list of items.
	 */
	List<Item> findAll();
	
	/**
	 * Returns specific item by filtering through Id.
	 * 
	 * @param Item Id
	 * @return Item by Id
	 */
	Item findById(long id);
	
}
