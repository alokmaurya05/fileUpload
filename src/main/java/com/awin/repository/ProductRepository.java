package com.awin.repository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.awin.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	

public List<Product> findByProductName(String productName);


@Query(value = "SELECT * from PRODUCT p WHERE p.PRODUCT_NAME =:productName",nativeQuery = true) 
public List<Product> findProductCustomQuery(@Param("productName") String productName);
 
@Query("SELECT p from Product p where p.customer= ?1")
public List<Product> findProductByCustomerId(int customer_id);

@Query("SELECT product FROM Product product " +"JOIN FETCH product.customer customer")
public List<Product> findAllProduct();

public List<Product> findTop10ByProductName(String productName) ;

}
