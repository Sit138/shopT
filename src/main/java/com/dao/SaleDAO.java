package com.dao;

import com.dto.FinalStatisticSaleForPeriod;
import com.dto.SaleProductInRangeDetailed;
import com.dto.StatisticOnSaleDTO;

import java.util.List;

public interface SaleDAO {

    List<StatisticOnSaleDTO> getStatisticOnSale(int firstResult, int maxCounRows);

    FinalStatisticSaleForPeriod getFinalStatisticSaleForPeriod();

    int numberItemsTheSaleRangeReport();

    void aggregateSalesOfProductInTheLastHour();//scheduler method
}
