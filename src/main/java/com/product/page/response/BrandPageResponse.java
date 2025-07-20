package com.product.page.response;

import java.util.List;

import com.product.dto.BrandDTO;

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
public class BrandPageResponse {

	private List<BrandDTO> brandDTOs;
	
	private PaginationInfo paginationInfo;
	
}
