package com.jbk.service;

import java.util.List;


import com.jbk.model.Product;

public interface ProductService {
	
	public int addProduct(Product product);
	public Product getProductById(long productId);

	public List<Product> getAllProducts();
	
	public int deleteProduct(long productId);
	
	public int updateProduct(Product product);
	
	public Product getProductByName(String productName);
	public List<Product> getProductByPriceRange(double minPrice, double maxPrice);
	public List<Product> getAllProductStartWith(String expression);
	public List<Product> sortProducts(String orderType, String field);
	public double getMaxPrice();
	public List<Product> getMaxPriceProduct();
}
