package com.awin.services;

import java.util.List;

import com.awin.dto.ProductSearchResponse;
import com.awin.entity.Product;
import com.github.fge.jsonpatch.JsonPatch;

public interface ProductService {

	public List<Product> getProductByName(String productName);
	public List<Product> getAllProduct();
	public List<Product> getProductByCustomerId(int customerId);
	public List<Product> getProductByNameCustomQuery(String productName);
	public Product updateProduct(JsonPatch patch, int productId);
	public List<ProductSearchResponse> getCustomersByProductName(String productName);
	
}
