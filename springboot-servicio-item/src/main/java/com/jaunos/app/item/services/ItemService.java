package com.jaunos.app.item.services;

import java.util.List;

import com.jaunos.app.item.model.Item;

public interface ItemService {
	
	public List<Item> findAll();
	
	public Item findById(Long id, Integer cantidad);

}
