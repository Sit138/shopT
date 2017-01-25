package service;

import dto.DiscountDTO;
import util.Pagination;
import util.enums.DiscountType;
import java.util.Date;
import java.util.List;

public interface DiscountService {

    void addProductDiscount(int productId, byte value, DiscountType type);

    List<DiscountDTO> selectHistoryProductDiscounts(Pagination pagination);

    DiscountDTO getNowAutoDiscountProduct();

    int countItemsDiscountHistory();

    void insertEndDateDiscount(Date endDateDiscount, int productId);

}
