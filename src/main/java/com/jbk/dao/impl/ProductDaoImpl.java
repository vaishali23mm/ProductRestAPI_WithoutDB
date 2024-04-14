package com.jbk.dao.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import org.springframework.core.MethodParameter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.jbk.dao.ProductDao;
import com.jbk.exception.ResourceAlreadyExistException;
import com.jbk.exception.ResourceNotExistsException;
import com.jbk.exception.SomethingWentWrongException;
import com.jbk.model.Product;

@Component
public class ProductDaoImpl implements ProductDao {

	List<Product> list = new ArrayList<>();
	private String ExceptionError = "Something Went Wrong During operation";

	public ProductDaoImpl() {
		list.add(new Product(1, "xyz", 10, 100, "2023-01-12", "2025-02-04"));
		list.add(new Product(2, "abc", 10, 100, "2023-01-12", "2025-02-04"));
		list.add(new Product(3, "pqr", 10, 100, "2023-01-12", "2025-02-04"));
	}

	@Override
	public int addProduct(Product product) {

		try {

			for (Product listProduct : list) {
				if (listProduct.getProductName().equalsIgnoreCase(product.getProductName())) {

					throw new ResourceAlreadyExistException(
							"Product Already Exists . Product Name = " + product.getProductName());
				}
			}

			list.add(product);

			return 1;
		}

		catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("Something Went Wrong While Add Product");
		}

	}

	@Override
	public Product getProductById(long productId) {

		try {
			for (Product product : list) {

				if (product.getProductId() == productId) {
					return product;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("Something Went Wrong While Retrive Product");
		}

		throw new ResourceNotExistsException("Product Not Found With Id >> " + productId);
	}

	@Override
	public List<Product> getAllProducts() {

		return list;
	}

	@Override
	public int deleteProduct(long productId) {
		try {
			if (!list.isEmpty()) {

				boolean isDeleted = list.removeIf(product -> product.getProductId() == productId);
				if (isDeleted)
					return 1;

//				for (Product product : list) {
//					if(product.getProductId()==productId){
//						list.remove(product);
//						return 1;
//					}
//					
//				}
			} else {
				throw new ResourceNotExistsException("Product Not Exists to delete, List Is Empty ");
			}

		} catch (ResourceNotExistsException e) {
			throw new ResourceNotExistsException("Product Not Exists to delete, List Is Empty ");
		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("Something Went Wrong While Delete");
		}
		throw new ResourceNotExistsException("Product Not Exists to delete , Id  = " + productId);

	}

	@Override
	public int updateProduct(Product product) {
		try {

			int status = list.stream().filter(listProduct -> listProduct.getProductId() == product.getProductId())
					.peek(listProduct -> list.set(list.indexOf(listProduct), product)).mapToInt(e -> 1).findFirst()
					.orElse(0);

			return status;

		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException(ExceptionError);
		}
	}

	@Override
	public Product getProductByName(String productName) {
		if (!list.isEmpty()) {
			try {
				for (Product product : list) {
					if (product.getProductName().equalsIgnoreCase(productName)) {
						return product;
					}
				}

			}

			catch (Exception e) {
				e.printStackTrace();
				throw new SomethingWentWrongException(ExceptionError);
			}
		} else {
			throw new ResourceNotExistsException("List Is Empty");
		}

		throw new ResourceNotExistsException("Product Not Exists With Name : " + productName);
	}

	/**
	 *
	 */
	@Override
	public List<Product> getProductByPriceRange(double minPrice, double maxPrice) {
		if (!list.isEmpty()) {
			List<Product> products = list.stream()
					.filter(product -> product.getProductPrice() >= minPrice && product.getProductPrice() <= maxPrice)
					.collect(Collectors.toList());

			if (products != null) {
				return products;
			} else {
				throw new ResourceNotExistsException("Product Not Exists Within Price Range");
			}
		} else {
			throw new ResourceNotExistsException("List Is Empty");
		}

	}

	@Override
	public List<Product> getAllProductStartWith(String expression) {
		if (!list.isEmpty()) {
			List<Product> products = list.stream().filter(product -> product.getProductName().startsWith(expression))
					.collect(Collectors.toList());

			if (products != null) {
				return products;
			} else {
				throw new ResourceNotExistsException("Product Not Exists Within Price Range");
			}
		} else {
			throw new ResourceNotExistsException("List Is Empty");
		}
	}

	@Override
	public List<Product> sortProducts(String orderType, String field) {
		if (!list.isEmpty()) {

			Comparator<Product> comparator = null;
			switch (field) {
			case "id": {
				comparator = Comparator.comparing(Product::getProductId);

				break;
			}

			case "name": {
				comparator = Comparator.comparing(Product::getProductName);

				break;
			}

			case "price": {
				comparator = Comparator.comparing(Product::getProductPrice);

				break;
			}

			default:

				break;

			}
			if (orderType.equals("desc")) {
				comparator = comparator.reversed();
			}

			return list.stream().sorted(comparator).collect(Collectors.toList());

		} else {
			throw new ResourceNotExistsException("List Is Empty");
		}

	}

	@Override
	public double getMaxPrice() {
		if (!list.isEmpty()) {
			return list.stream().mapToDouble(Product::getProductPrice).max().getAsDouble();

		} else {
			throw new ResourceNotExistsException("List Is Empty");
		}

	}

	@Override
	public List<Product> getMaxPriceProduct() {
		double maxPrice = getMaxPrice();
		try {
			return list.stream().filter(product -> product.getProductPrice() == maxPrice).collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("Something Went Wrong While Retrive Product");
		}

	}

}
