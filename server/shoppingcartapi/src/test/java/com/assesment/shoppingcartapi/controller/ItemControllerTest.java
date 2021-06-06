package com.assesment.shoppingcartapi.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.assesment.shoppingcartapi.coreservice.ItemService;
import com.assesment.shoppingcartapi.dto.ItemPriceDto;
import com.assesment.shoppingcartapi.entity.Item;
import com.assesment.shoppingcartapi.enumerations.Measurement;
import com.assesment.shoppingcartapi.objectbuilder.ItemBuilder;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(SpringExtension.class)
@WebMvcTest(ItemController.class)
public class ItemControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ItemService itemService;
	
	@Test
	public void getPrice_CalculatePrice() throws Exception {
		
		Item itemPenguin = ItemBuilder.buildPenguin();
		
		MultiValueMap<String, String> parms = new LinkedMultiValueMap<String, String>();
		parms.add("qty", "50");
		parms.add("type", "UNIT");
		
		given(itemService.getPrice(itemPenguin.getItemId(),50, Measurement.UNIT)).willReturn(new ItemPriceDto(itemPenguin.getItemId(),itemPenguin.getItemName(),50,464));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/items/1/price/")
				.params(parms))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	public void getItems_FindAllItems() throws Exception {
		
		List<Item> items = new ArrayList<Item>();
		
		Item itemPenguin = ItemBuilder.buildPenguin();
		Item itemHorseshoe = ItemBuilder.buildHorseshoe();
		
		items.add(itemPenguin);
		items.add(itemHorseshoe);
		
		given(itemService.getItems()).willReturn(items);
		
		mockMvc.perform(MockMvcRequestBuilders
				.get("/items"))
				.andDo(print())
				.andExpect(status()
				.isOk());
		
	}
	
	@Test
	public void getPriceList_getAllPriceList() throws Exception {
		
		Item itemPenguin = ItemBuilder.buildPenguin();
		Item itemHorseshoe = ItemBuilder.buildHorseshoe();
		
		int qty = 5;
		
		List<ItemPriceDto> itemPriceDtoList = new ArrayList<ItemPriceDto>();
		itemPriceDtoList.add(new ItemPriceDto(itemPenguin.getItemId(),itemPenguin.getItemName(),40,350));
		itemPriceDtoList.add(new ItemPriceDto(itemPenguin.getItemId(),itemPenguin.getItemName(),12,137));
		itemPriceDtoList.add(new ItemPriceDto(itemHorseshoe.getItemId(),itemHorseshoe.getItemName(),30,4455));
		itemPriceDtoList.add(new ItemPriceDto(itemHorseshoe.getItemId(),itemHorseshoe.getItemName(),31,4650));
		itemPriceDtoList.add(new ItemPriceDto(itemHorseshoe.getItemId(),itemHorseshoe.getItemName(),32,4884));
		
		given(itemService.getPriceList(qty)).willReturn(itemPriceDtoList);
		
        mockMvc.perform(MockMvcRequestBuilders.get("/items/pricelist").param("qty", String.valueOf(qty)))
        .andDo(print())
        .andExpect(jsonPath("$", hasSize(qty)))
        .andExpect(jsonPath("$[0].id").exists())
        .andExpect(jsonPath("$[0].name").exists())
        .andExpect(jsonPath("$[0].unit").exists())
        .andExpect(jsonPath("$[0].price").exists())
        .andExpect(status().isOk());
		
	}

}
