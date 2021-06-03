package com.awin.error;

public class ProductNotFoundException extends RuntimeException{

	/** 
	 */
	private static final long serialVersionUID = -2782641774537561447L;
	
	public ProductNotFoundException(String massage) {super(massage);}

}
