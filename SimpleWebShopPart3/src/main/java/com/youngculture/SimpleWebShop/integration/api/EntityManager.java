package com.youngculture.SimpleWebShop.integration.api;

import java.util.List;

import com.youngculture.SimpleWebShop.integration.entities.CartEntity;
import com.youngculture.SimpleWebShop.integration.entities.Entity;

public interface EntityManager {

	List<Entity> readTable(String category);

	void updateCartDataBase(CartEntity cartEntity);

	void emptyCartDataBase();

	List<Entity> readCartTable(String category);

	void initializeDataBase();

}
