package com.dao;

import com.dto.SaleProductInRangeDetailed;
import com.dto.TotalSaleReport;

import java.util.List;

public interface SaleDAO {

    List<TotalSaleReport> totalSaleReport();

    List<SaleProductInRangeDetailed> saleListInRangePagination(int pageId, int maxResults);

    int numberItemsTheSaleRangeReport();
}
