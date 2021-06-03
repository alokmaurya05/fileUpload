package com.awin.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductSearchResponse {
    
	private int productId ; 
    private String productName;
	private String customerName;
	private String customerCity ;
	
	public ProductSearchResponse( int id , String productName, String customerName)
	{
		this.productId = id ;
		this.productName = productName ;
		this.customerName = customerName ;
	}
}
