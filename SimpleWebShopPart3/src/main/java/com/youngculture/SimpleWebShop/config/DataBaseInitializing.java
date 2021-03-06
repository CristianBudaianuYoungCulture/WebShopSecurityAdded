package com.youngculture.SimpleWebShop.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.youngculture.SimpleWebShop.integration.repo.ProductRepository;

public class DataBaseInitializing implements InitializingBean {

	@Autowired
	private ProductRepository productRepositoryBean;

	public DataBaseInitializing(ProductRepository productRepositoryBean) {
		super();
		this.productRepositoryBean = productRepositoryBean;
	}

	@Override
	public void afterPropertiesSet() {
		System.out.println("Data Base is initialized!");
		productRepositoryBean.initializeDataBase();

	}

}
