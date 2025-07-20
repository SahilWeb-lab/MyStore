package com.product.page.response;

import java.util.List;

import com.product.dto.CategoryDTO;

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
public class CategoryPageResponse {

	private List<CategoryDTO> categories;
	
	private PaginationInfo paginationInfo;
	
}
