package com.awin.servicesImpl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.awin.dto.ProductSearchResponse;
import com.awin.entity.Product;
import com.awin.error.ProductException;
import com.awin.error.ProductNotFoundException;
import com.awin.repository.ProductRepository;
import com.awin.services.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;


@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger logger = LogManager.getLogger(ProductServiceImpl.class);
   @PersistenceContext
   private EntityManager em;
   
   private ProductRepository productRepo;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository,EntityManager entityManager) {
		this.productRepo = productRepository;
		this.em = entityManager ;
	}
	@Override
	public List<Product> getProductByName(String productName) {
        logger.info("Getting product by Name{}=", productName);
		return productRepo.findByProductName(productName);
	}

	@Override
	public List<Product> getAllProduct() {
		logger.info("Get All the product  ");
		return productRepo.findAllProduct();
	}
    @Transactional(readOnly = true)
	@Override
	public List<Product> getProductByCustomerId(int customerId) {
		logger.info("Getting product by customer Id{}=", customerId);
		return productRepo.findProductByCustomerId(customerId);
	}

	@Override
	public List<Product> getProductByNameCustomQuery(String productName) {

		return productRepo.findProductCustomQuery(productName);
	}

	@Override
	public Product updateProduct(JsonPatch patch, int productId) {
		logger.info("Trying to fetch product from DB ProductId {}", productId);
		Product updatedProduct = null;
		Optional<Product> originalProduct = productRepo.findById(productId);
		if (!originalProduct.isPresent()) {
			logger.error("product not found with id{}", productId);
			throw new ProductNotFoundException("Product Not Found");
		}
		try {
			Product needtoBeupdateProduct = applyPatchToProduct(patch, originalProduct.get());
			needtoBeupdateProduct.setCustomer(originalProduct.get().getCustomer());
			updatedProduct = productRepo.saveAndFlush(needtoBeupdateProduct);
		} catch (Exception e) {
			logger.error("Error updating product with product Id{}", productId, " error is like ", e.getMessage());
			throw new ProductException(e.getMessage());
		}
		return updatedProduct;
	}

	@Override
	public List<ProductSearchResponse> getCustomersByProductName(String productName) {
		TypedQuery<ProductSearchResponse> query = em.createQuery("SELECT new com.awin.dto.ProductSearchResponse(p.id,p.productName,c.name,c.city)"
				                                        +"FROM Product p JOIN p.customer c where p.productName LIKE :product_Name", ProductSearchResponse.class);
		query.setParameter("product_Name","%".concat(productName));
		return query.getResultList();
	}
	
	private Product applyPatchToProduct(JsonPatch patch, Product tragetDBProduct)
			throws JsonPatchException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode patchedProduct = patch.apply(objectMapper.convertValue(tragetDBProduct, JsonNode.class));
		return objectMapper.treeToValue(patchedProduct, Product.class);
	}
	
}
