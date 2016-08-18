package com.service;

import com.dao.SaleDAO;
import com.dto.SaleProductInRangeDetailed;
import com.dto.TotalSaleReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleDAO saleDAO;

    @Override
    @Transactional(readOnly = true)
    public List<SaleProductInRangeDetailed> saleListInRange() {
        return saleDAO.saleListInRange();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TotalSaleReport> totalSaleReport() {
        return saleDAO.totalSaleReport();
    }

}
