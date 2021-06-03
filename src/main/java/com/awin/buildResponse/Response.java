package com.awin.buildResponse;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.awin.dto.CustomerResponse;
import com.awin.dto.ProductResponse;
import com.awin.dto.ProductSearchResponse;
import com.awin.entity.Customer;
import com.awin.entity.Product;



/*This class use to Build response. */
public class Response
{
    private static final String SUCCESS = "SUCCESS";
    private static final String FAIL = "FAIL";
    private String status = SUCCESS;
    private String message = null;
    private Object data = null;

    
    public static ResponseEntity<?> success(Object data ,HttpStatus status)
    {
        return new ResponseEntity<Object>(data,status);
    }
    public static ResponseEntity<List<Customer>> success(List<Customer> data ,HttpStatus status)
    {
        return new ResponseEntity<List<Customer>>(data,status);
    }
    public static ResponseEntity<List<ProductSearchResponse>> productSearchSuccess(List<ProductSearchResponse> data ,HttpStatus status)
    {
        return new ResponseEntity<List<ProductSearchResponse>>(data,status);
    }
    public static ResponseEntity<ProductResponse> success(ProductResponse data ,HttpStatus status)
    {
        return new ResponseEntity<ProductResponse>(data,status);
    }
    public static ResponseEntity<Product> success(Product data ,HttpStatus status)
    {
        return new ResponseEntity<Product>(data,status);
    }
    public static ResponseEntity<CustomerResponse> success(CustomerResponse data ,HttpStatus status)
    {
        return new ResponseEntity<CustomerResponse>(data,status);
    }
    public static Response success(String message)
    {
        return new Response(SUCCESS, message);
    }
    public static ResponseEntity<String> success(String message, HttpStatus status)
    {
        return new ResponseEntity<String>(message, status);
    }
    public static Response success(String message, Object data)
    {
        return new Response(SUCCESS, message, data);
    }
    public static ResponseEntity<?> fail(Object data ,HttpStatus status)
    {
        return new ResponseEntity<Object>(data,status);
    }
    public static ResponseEntity<CustomerResponse> fail(CustomerResponse massage,HttpStatus status)
    {
        return new ResponseEntity<CustomerResponse>(massage,status);
    }
    
    public static ResponseEntity<List<Customer>> fail(HttpStatus status)
    {
        return new ResponseEntity<List<Customer>>(status);
    }
    
    public static ResponseEntity<Product> fail(Product product,HttpStatus status)
    {
        return new ResponseEntity<Product>(product,status);
    }
    
    

    private Response()
    {

    }

    private Response(String status, String message, Object data)
    {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    private Response(String status, String message)
    {
        this(status, message, null);
    }

    public static String getSUCCESS()
    {
        return SUCCESS;
    }

    public static String getFAIL()
    {
        return FAIL;
    }

    public String getStatus()
    {
        return status;
    }

    public String getMessage()
    {
        return message;
    }

    public Object getData()
    {
        return data;
    }
}
