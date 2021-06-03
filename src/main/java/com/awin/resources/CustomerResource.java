package com.awin.resources;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




import com.awin.buildResponse.Response;
import com.awin.dto.CustomerRequest;
import com.awin.dto.CustomerResponse;
import com.awin.dto.ProductResponse;
import com.awin.dto.ProductSearchResponse;
import com.awin.entity.Customer;
import com.awin.entity.Product;
import com.awin.services.CustomerService;
import com.awin.services.ProductService;



@RestController
@RequestMapping("/api/customer")
public class CustomerResource {

	public static final Logger logger = LogManager.getLogger(CustomerResource.class);
	private CustomerService customerService;
	private ProductService productService ;
	
	@Autowired
	public CustomerResource(CustomerService customerService,ProductService productService)
	{
		this.customerService = customerService ;
		this.productService = productService ;
	}
	
	@PostMapping (value = "/add")
    public ResponseEntity<String> SaveCustomer(@RequestBody CustomerRequest customerRequest) throws Exception
    {
		
		logger.info("Customer request start ");
        try {
        	customerService.saveCustomer(customerRequest.getCustomer());
		} catch (Exception e) {
		  logger.error("Error in save Customer ", e.getMessage());
		  throw new Exception(e.getMessage());
		}
        logger.info("Save Customer with name  ", customerRequest.getCustomer().getName());
		return Response.success("Customer Save Succefully", HttpStatus.CREATED);
    	
    }
	@GetMapping(value="/id/{id}")
    public ResponseEntity<CustomerResponse> showAllCustomer(@PathVariable(name = "id") int customerid)
	{
		
	    Optional<Customer>  customer =	customerService.showCustomer(customerid);
	    CustomerResponse customerRes =null ;
	    if(customer.isPresent()) {
	    	customerRes =new CustomerResponse();
	    	 return Response.success(customerRes, HttpStatus.OK);
	    }
		return Response.fail(customerRes,HttpStatus.NOT_FOUND)	;
		
	}
	
	@GetMapping(value="/city/{cityName}")
    public ResponseEntity<List<Customer>> showCustomerByCity(@PathVariable(name = "cityName") String cityName)
	{
		List<Customer> customerList =	customerService.showCustomerByCity(cityName);
	    if(
	    		! customerList.isEmpty() ) {
	    	 return Response.success(customerList, HttpStatus.OK);
	    }
		return Response.fail(HttpStatus.NOT_FOUND)	;
		
	}
	
	@GetMapping(value = "/product/{customerId}")
	public ResponseEntity<ProductResponse> productByCustomerId(@PathVariable(name = "customerId") int customerId){
		
		Optional<Customer> customer =  customerService.showCustomer(customerId);
		ProductResponse productRes = new ProductResponse() ;
		if(customer.isPresent())
		{
	    List<Product> productList = productService.getProductByCustomerId(customerId);
		productRes.setCustomerName(customer.get().getName());
		productRes.setProduct(productList);
		}
		else {
			Response.fail("Customer ID not found", HttpStatus.NOT_FOUND);
		}
		
		return Response.success(productRes, HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/productName/{productName}")
	public ResponseEntity<List<ProductSearchResponse>> searchProductByName(@PathVariable String productName) {
		logger.info("Searching all the customer having product name {}",productName);
		List<ProductSearchResponse> productList =	productService.getCustomersByProductName(productName);
		 return Response.productSearchSuccess(productList, HttpStatus.OK);
	}
}
