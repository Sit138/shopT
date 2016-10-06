package com.service;

import com.dto.FinalStatisticSaleForPeriod;
import com.dto.StatisticOnSaleDTO;
import com.dto.util.PaginationBuilder;

import java.util.List;

public interface SaleService {

    List<StatisticOnSaleDTO> getStatisticOnSale(PaginationBuilder paginationBuilder);

    FinalStatisticSaleForPeriod getFinalStatisticSaleForPeriod();

    int numberItemsTheSaleRangeReport();

    void aggregateSalesOfProductInTheLastHour();
}

