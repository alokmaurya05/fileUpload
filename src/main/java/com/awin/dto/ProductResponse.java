package com.awin.dto;

import java.util.List;

import com.awin.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

	private String customerName;
	private List<Product> product ;
}
