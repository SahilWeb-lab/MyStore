package com.product.page.response;

import java.util.List;

import com.product.dto.ProductDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductPageResponse {

	private List<ProductDTO> products;

	private PaginationInfo paginationInfo;
}