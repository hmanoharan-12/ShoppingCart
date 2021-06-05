package com.assesment.shoppingcartapi.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.assesment.shoppingcartapi.entity.Item;
import com.assesment.shoppingcartapi.objectbuilder.ItemBuilder;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ItemDaoTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private ItemDao itemDao;
	
	@Test
	public void testFindAll() {
		
		Item expectedItem1 = entityManager.merge(ItemBuilder.buildPenguin());
		Item expectedItem2 = entityManager.merge(ItemBuilder.buildHorseshoe());
		
		List<Item> expectedItemList = new ArrayList<Item>();
		
		expectedItemList.add(expectedItem1);
		expectedItemList.add(expectedItem2);
		
		List<Item> items = itemDao.findAll();
		
		assertThat(items.equals(expectedItemList));
		
	}
}

