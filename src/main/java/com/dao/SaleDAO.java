package com.dao;

import com.dto.SaleProductInRangeDetailed;
import com.dto.SumDiscountByRange;
import com.dto.TotalSaleReport;

import java.util.List;

public interface SaleDAO {
    public List<SaleProductInRangeDetailed> saleListInRange();
    public List<TotalSaleReport> totalSaleReport();
    public List<SumDiscountByRange> sumDiscountByRange();
}
