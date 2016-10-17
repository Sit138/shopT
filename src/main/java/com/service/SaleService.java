package com.service;

import com.dto.FinalStatisticSaleForPeriod;
import com.dto.StatisticOnSaleDTO;
import com.dto.util.FilterStatisticSale;
import com.dto.util.PaginationBuilder;

import java.util.Date;
import java.util.List;

public interface SaleService {

    List<StatisticOnSaleDTO> getStatisticOnSale(FilterStatisticSale paginationBuilder);

    FinalStatisticSaleForPeriod getFinalStatisticSaleForPeriod(Date from, Date to);

    int numberItemsTheSaleRangeReport(Date from, Date to);

    void aggregateSalesOfProductInTheLastHour();

    void insertProductSale(int id);
}

