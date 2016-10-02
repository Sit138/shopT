package com.service;


import com.dto.FinalStatisticSaleForPeriod;
import com.dto.SaleProductInRangeDetailed;
import com.dto.StatisticOnSaleDTO;

import java.util.List;

public interface SaleService {

    List<StatisticOnSaleDTO> getStatisticOnSale(int firstResult, int maxCounRows);

    FinalStatisticSaleForPeriod getFinalStatisticSaleForPeriod();

    int numberItemsTheSaleRangeReport();

    void aggregateSalesOfProductInTheLastHour();
}

