package com.service;

import com.dao.SaleDAO;
import com.dto.SaleProductInRangeDetailed;
import com.dto.TotalSaleReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleDAO saleDAO;

    @Override
    public List<SaleProductInRangeDetailed> saleListInRangePagination(int pageId, int maxResults) {
        return saleDAO.saleListInRangePagination(pageId, maxResults);
    }

    @Override
    public List<TotalSaleReport> totalSaleReport() {
        return saleDAO.totalSaleReport();
    }

    @Override
    public int numberItemsTheSaleRangeReport() {
        return saleDAO.numberItemsTheSaleRangeReport();
    }

    @Override
    @Transactional
    public void aggregateSalesOfProductInTheLastHour() {
        saleDAO.aggregateSalesOfProductInTheLastHour();
    }

}
