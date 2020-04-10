package com.youngculture.SimpleWebShop.integration.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.youngculture.SimpleWebShop.integration.entities.CartEntity;
import com.youngculture.SimpleWebShop.integration.entities.Entity;
import com.youngculture.SimpleWebShop.utility.WebShopConstants;

public class EntityManagerBean implements EntityManager {

	@Autowired
	private DBObjectsFactory dBObjectsFactory;

	@Autowired
	private JSonDBManager jSonDBManager;

	
	public EntityManagerBean(DBObjectsFactory dBObjectsFactory, JSonDBManager jSonDBManager) {
		super();
		this.dBObjectsFactory = dBObjectsFactory;
		this.jSonDBManager = jSonDBManager;
	}

	@Override
	public void updateDataBase() {
		String filePath;

		List<Entity> entities = dBObjectsFactory.populateDiamonds();
		filePath = WebShopConstants.DIAMOND_PATH;
		jSonDBManager.updateJSonDataBase(filePath, entities);

		entities = dBObjectsFactory.populateCars();
		filePath = WebShopConstants.CARS_PATH;
		jSonDBManager.updateJSonDataBase(filePath, entities);

		entities = dBObjectsFactory.populateShoes();
		filePath = WebShopConstants.SHOES_PATH;
		jSonDBManager.updateJSonDataBase(filePath, entities);

	}

	@Override
	public List<Entity> readTable(String category) {
		if (category == null) {
			throw new IllegalStateException("Missing category");
		}

		List<Entity> entities;
		try {
			entities = jSonDBManager.readJSonDataBase(category, getPathByCategory(category));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return entities;

	}

	@Override
	public List<Entity> readCartTable(String category) {
		if (category == null) {
			throw new IllegalStateException("Missing category");
		}
		String username = extractUserName();
		List<Entity> entities;
		try {
			
			entities = jSonDBManager.readJSonDataBase(category, WebShopConstants.CART_PATH_1 + username + WebShopConstants.CART_PATH_2);
		} catch (IOException e) {
			entities = new ArrayList<>();
		}
		return entities;
	}

	private String getPathByCategory(String category) {
		switch (category) {
		case WebShopConstants.DIAMONDS:
			return WebShopConstants.DIAMOND_PATH;
		case WebShopConstants.SHOES:
			return WebShopConstants.SHOES_PATH;
		case WebShopConstants.CARS:
			return WebShopConstants.CARS_PATH;
		default:
			return WebShopConstants.CART_PATH;
		}
	}

	@Override
	public void updateCartDataBase(CartEntity cartEntity) {
		String username = extractUserName();
		jSonDBManager.updateCartDataBase(WebShopConstants.CART_PATH_1 + username + WebShopConstants.CART_PATH_2, cartEntity);

	}

	@Override
	public void emptyCartDataBase(String item) {
		String username = extractUserName();
		jSonDBManager.emptyJSonDataBase(WebShopConstants.CART_PATH_1 + username + WebShopConstants.CART_PATH_2);

	}

	private String extractUserName() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		return username;
	}

}