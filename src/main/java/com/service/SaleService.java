package com.service;


import com.dto.SaleProductInRangeDetailed;
import com.dto.SumDiscountByRange;
import com.dto.TotalSaleReport;

import java.util.List;

public interface SaleService {
    public List<SaleProductInRangeDetailed> saleListInRange();
    public List<TotalSaleReport> totalSaleReport();
    public List<SumDiscountByRange> sumDiscountByRange();
}

