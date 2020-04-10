package com.youngculture.SimpleWebShop.integration.repo;

import java.util.List;

import com.youngculture.SimpleWebShop.integration.entities.CartEntity;
import com.youngculture.SimpleWebShop.integration.entities.Entity;

public interface ProductRepository {

	List<Entity> retriveProducts(String category);

	void updateCartDataBase(CartEntity cartEntity);

	List<Entity> retriveCartProducts(String category);

	void emptyCart();

	void initializeDataBase();

}
