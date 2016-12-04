package service;

import dto.DiscountDTO;
import model.Discount;
import model.Product;
import util.PaginationBuilder;
import model.enums.DiscountType;
import java.util.Date;
import java.util.List;

public interface DiscountService {

    void addProductDiscount(int productId, byte value, DiscountType type);

    List<DiscountDTO> selectHistoryProductDiscounts(PaginationBuilder paginationBuilder);

    DiscountDTO getNowAutoDiscountProduct();

    int numberItemsDiscountHistory();

    void insertEndDateDiscount(Date endDateDiscount, int productId);

    byte getValueByProductId(int id);

}
