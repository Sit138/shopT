package service;

import dto.DiscountDTO;
import util.PaginationBuilder;
import model.enums.DiscountType;
import java.util.Date;
import java.util.List;

public interface DiscountService {

    void insertProductDiscount();

    List<DiscountDTO> selectHistoryProductDiscounts(PaginationBuilder paginationBuilder);

    DiscountDTO getNowDiscountProduct();

    int numberItemsDiscountHistory();

    void insertEndDateDiscount(DiscountType discountType, Date endDateDiscount, int productId);

}
