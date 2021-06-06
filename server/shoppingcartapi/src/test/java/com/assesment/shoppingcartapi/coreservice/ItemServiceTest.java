package com.assesment.shoppingcartapi.coreservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.assesment.shoppingcartapi.dao.ItemDao;
import com.assesment.shoppingcartapi.dto.ItemPriceDto;
import com.assesment.shoppingcartapi.entity.Item;
import com.assesment.shoppingcartapi.enumerations.Measurement;
import com.assesment.shoppingcartapi.objectbuilder.ItemBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

	@Mock
	private ItemDao itemDao;
	
	@Autowired
	private ItemService itemService;
	
	@BeforeEach
    public void setUp() {
		itemService = new ItemService(itemDao);
    }
	
	@Test
	public void getPrice_getPricePerUnit() {
		
		Item itemPenguin = ItemBuilder.buildPenguin(); 
		given(itemDao.findById(itemPenguin.getItemId())).willReturn(itemPenguin);
		ItemPriceDto itemPricePenguinDto = itemService.getPrice(itemPenguin.getItemId(), 1, Measurement.UNIT);
		assertThat(itemPricePenguinDto.getPrice()).isEqualTo(11);
		
		Item itemHorseshoe = ItemBuilder.buildHorseshoe();
		given(itemDao.findById(itemHorseshoe.getItemId())).willReturn(itemHorseshoe);
		ItemPriceDto itemPriceHorseshoeDto = itemService.getPrice(itemHorseshoe.getItemId(), 1, Measurement.UNIT);
		assertThat(itemPriceHorseshoeDto.getPrice()).isEqualTo(215);
	}
	
	@Test
	public void getPrice_getPricePerCarton() {
		
		Item itemPenguin = ItemBuilder.buildPenguin();
		given(itemDao.findById(itemPenguin.getItemId())).willReturn(itemPenguin);
		ItemPriceDto itemPriceDto = itemService.getPrice(itemPenguin.getItemId(), 1, Measurement.CARTON);
		assertThat(itemPriceDto.getPrice()).isEqualTo(175);
		
		Item itemHorseshoe = ItemBuilder.buildHorseshoe();
		given(itemDao.findById(itemHorseshoe.getItemId())).willReturn(itemHorseshoe);
		ItemPriceDto itemPriceHorseshoeDto = itemService.getPrice(itemHorseshoe.getItemId(), 1, Measurement.CARTON);
		assertThat(itemPriceHorseshoeDto.getPrice()).isEqualTo(825);
	}
	
	@Test
	public void getPriceList() {
		Item itemPenguin = ItemBuilder.buildPenguin();
		Item itemHorseshoe = ItemBuilder.buildHorseshoe();
		
		List<Item> itemList = new ArrayList<Item>();
		itemList.add(itemPenguin);
		itemList.add(itemHorseshoe);
		
		given(itemDao.findAll()).willReturn(itemList);
		
		List<ItemPriceDto> itemPriceListDto = itemService.getPriceList(50);
		
		assertThat(itemPriceListDto.size()).isEqualTo(100);
		assertThat(itemPriceListDto.get(32).getPrice()).isEqualTo(323);
		assertThat(itemPriceListDto.get(97).getPrice()).isEqualTo(7326);
		assertThat(itemPriceListDto.get(42).getPrice()).isEqualTo(384);
		assertThat(itemPriceListDto.get(58).getPrice()).isEqualTo(1683);
		assertThat(itemPriceListDto.get(18).getPrice()).isEqualTo(216);
	}
}
