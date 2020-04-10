package com.youngculture.SimpleWebShop.integration.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import com.youngculture.SimpleWebShop.integration.api.EntityManager;
import com.youngculture.SimpleWebShop.integration.entities.CartEntity;
import com.youngculture.SimpleWebShop.integration.entities.Entity;

@Transactional
public class ProductRepositoryBean implements ProductRepository {

	@Autowired
	private EntityManager entityManagerBean;

	public ProductRepositoryBean(EntityManager entityManagerBean) {
		super();
		this.entityManagerBean = entityManagerBean;
	}

	@Override
	@Cacheable("products")
	public List<Entity> retriveProducts(String category) {
		return entityManagerBean.readTable(category);
	}

	@Override
	public void updateDataBase() {
		entityManagerBean.updateDataBase();

	}

	@Override
	public void updateCartDataBase(CartEntity cartEntity) {
		entityManagerBean.updateCartDataBase(cartEntity);

	}

	@Override
	public List<Entity> retriveCartProducts(String category) {
		return entityManagerBean.readCartTable(category);
	}

	@Override
	public void emptyCart(String item) {
		entityManagerBean.emptyCartDataBase(item);

	}

	
}
