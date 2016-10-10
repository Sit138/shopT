package com.dao;

import com.dto.DiscountDTO;
import com.dto.util.PaginationBuilder;

import java.util.Date;
import java.util.List;

public interface DiscountDAO {

    List<DiscountDTO> selectHistoryProductDiscounts(PaginationBuilder paginationBuilder);

    DiscountDTO getNowDiscountProduct();

    void insertEndDateDiscount(int addTypeDiscount, Date endDateDiscount, int productId);

    int numberItemsDiscountHistory();

}
