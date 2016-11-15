package dao;

import dto.FinalStatisticSaleForPeriod;
import dto.StatisticOnSaleDTO;
import dto.util.FilterStatisticSale;
import java.util.Date;
import java.util.List;

public interface SaleDAO {

    List<StatisticOnSaleDTO> getStatisticOnSale(FilterStatisticSale paginationBuilder);

    FinalStatisticSaleForPeriod getFinalStatisticSaleForPeriod(Date from, Date to);

    int numberItemsTheSaleRangeReport(Date from, Date to);

    void aggregateSalesOfProductInTheLastHour();//scheduler method
}
