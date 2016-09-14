package com.service;


import com.dto.SaleProductInRangeDetailed;
import com.dto.TotalSaleReport;

import java.util.List;

public interface SaleService {
    List<SaleProductInRangeDetailed> saleListInRangePagination(int pageId, int maxResults);

    List<TotalSaleReport> totalSaleReport();

    int numberItemsTheSaleRangeReport();
}

