package service;

import dto.DiscountDTO;
import dto.util.PaginationBuilder;
import model.DiscountType;
import java.util.Date;
import java.util.List;

public interface DiscountService {

    void insertProductDiscount();

    List<DiscountDTO> selectHistoryProductDiscounts(PaginationBuilder paginationBuilder);

    DiscountDTO getNowDiscountProduct();

    int numberItemsDiscountHistory();

    void insertEndDateDiscount(DiscountType discountType, Date endDateDiscount, int productId);

}
