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

	List<Item> findAll();
}
