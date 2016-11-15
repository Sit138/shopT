package service;

import dto.FinalStatisticSaleForPeriod;
import dto.StatisticOnSaleDTO;
import dto.util.FilterStatisticSale;
import java.util.Date;
import java.util.List;

public interface SaleService {

    List<StatisticOnSaleDTO> getStatisticOnSale(FilterStatisticSale paginationBuilder);

    FinalStatisticSaleForPeriod getFinalStatisticSaleForPeriod(Date from, Date to);

    int numberItemsTheSaleRangeReport(Date from, Date to);

    void aggregateSalesOfProductInTheLastHour();

    void insertProductSale(int id);
}

