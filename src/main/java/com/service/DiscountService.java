package com.service;


import com.dto.DiscountDTO;
import com.dto.util.PaginationBuilder;

import java.util.Date;
import java.util.List;

public interface DiscountService {

    void insertProductDiscount();

    List<DiscountDTO> selectHistoryProductDiscounts(PaginationBuilder paginationBuilder);

    DiscountDTO getNowDiscountProduct();

    int numberItemsDiscountHistory();

    void insertEndDateDiscount(int addTypeDiscount, Date endDateDiscount, int productId);

}
