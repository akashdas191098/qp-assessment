package com.qa.grocery.response;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GroceryResponseWithHeader {
	
    private Integer currentPage;
    private Integer totalRecordPerPage;
    private Long totalRecords;
    private Integer totalPages;
    private List<GroceryShowResponse> groceries;
}
