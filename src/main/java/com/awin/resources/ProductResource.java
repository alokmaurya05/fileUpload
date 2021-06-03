package com.awin.resources;

import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.awin.buildResponse.Response;
import com.awin.entity.Product;
import com.awin.error.ProductException;
import com.awin.services.ProductService;
import com.github.fge.jsonpatch.JsonPatch;

@RestController
@RequestMapping(value = "/api/product")
public class ProductResource {

	private static final Logger logger = LogManager.getLogger(ProductResource.class);
	@Autowired
	private ProductService productService;

	@GetMapping(value = "/all")
	public ResponseEntity<?> getAllProduct() {
		logger.info("Fetching all product details ");
		List<Product> products = null;
		try {
			products = productService.getAllProduct();

			if (products.isEmpty()) {
				logger.info("No product details found ");
				return Response.fail("No Data Available", HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			logger.error("error in getting all product ", e.getMessage());
			throw new ProductException(e.getMessage());
		}

		logger.info("Fetching All product done no of product {}", products.size());
		return Response.success(products, HttpStatus.OK);

	}

	@GetMapping(value = "/{productName}")
	public ResponseEntity<?> getAllProductByCustomer(@PathVariable("productName") String product_Name) {
		logger.info("Getting product with name {} ",product_Name);
		List<Product> products = productService.getProductByName(product_Name);
		return Response.success(products, HttpStatus.OK);

	}
	
	@PatchMapping (value = "/{id}", consumes = "application/json-patch+json")
	public ResponseEntity<Product> update(@PathVariable(name = "id") int id, @RequestBody JsonPatch updateProductPatch) throws Exception {
		
		logger.info("Update for product with id {} ",id);
		Product product = productService.updateProduct(updateProductPatch, id);
		if(Objects.isNull(product)) {
			logger.error("Error in product update request {} ",id);
			return Response.fail(product, HttpStatus.NOT_MODIFIED);
		}
		return Response.success(product, HttpStatus.OK);
		
	}
	 
}
