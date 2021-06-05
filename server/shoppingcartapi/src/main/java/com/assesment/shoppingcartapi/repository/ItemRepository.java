package com.assesment.shoppingcartapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.assesment.shoppingcartapi.entity.Item;

/**
 * Generic interface to perform CRUD operations on Item Entity.
 * 
 * @author harrymanoharan
 *
 */
public interface ItemRepository extends CrudRepository<Item, Long> {

}
