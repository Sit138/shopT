package com.service;

import com.dao.DiscountDAO;
import com.dao.ProductDAO;
import com.dao.SaleDAO;
import com.dto.DiscountDTO;
import com.dto.FinalStatisticSaleForPeriod;
import com.dto.StatisticOnSaleDTO;
import com.dto.util.FilterStatisticSale;
import com.dto.util.PaginationBuilder;
import com.model.Product;
import com.model.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleDAO saleDAO;

    @Autowired
    private DiscountDAO discountDAO;

    @Autowired
    private ProductDAO productDAO;

    @Override
    public List<StatisticOnSaleDTO> getStatisticOnSale(FilterStatisticSale paginationBuilder) {
        return saleDAO.getStatisticOnSale(paginationBuilder);
    }

    @Override
    public FinalStatisticSaleForPeriod getFinalStatisticSaleForPeriod(Date from, Date to) {
        return saleDAO.getFinalStatisticSaleForPeriod(from, to);
    }

    @Override
    public int numberItemsTheSaleRangeReport(Date from, Date to) {
        return saleDAO.numberItemsTheSaleRangeReport(from, to);
    }

    @Override
    @Transactional
    public void aggregateSalesOfProductInTheLastHour() {
        saleDAO.aggregateSalesOfProductInTheLastHour();
    }

    @Override
    @Transactional
    public void insertProductSale(int id) {
        Product productSale = productDAO.getProduct(id);
        Sale sale = createSale(productSale);
        productSale.addProductSale(sale);
        productDAO.saveOrUpdate(productSale);
    }

    private Sale createSale(Product productSale){
        Date currentDate = new Date();
        BigDecimal saleAmount = productSale.getPrice();
        DiscountDTO discountProduct = discountDAO.getNowDiscountProduct();
        if(discountProduct != null && (productSale.getId() == discountProduct.getProductId())){
            double valueDiscount = discountProduct.getValue() / 100;
            saleAmount = saleAmount.subtract(saleAmount.multiply(new BigDecimal(valueDiscount)));
        }
        Sale sale = new Sale(saleAmount, productSale.getPrice(), currentDate);
        return sale;
    }

}
