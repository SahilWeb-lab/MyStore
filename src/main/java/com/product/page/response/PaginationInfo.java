package com.product.page.response;

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
public class PaginationInfo {

	private Integer currentPage;

	private Integer totalPages;

	private Long totalItems;

	private Boolean lastPage;

}
