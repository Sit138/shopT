package dao;

import dto.DiscountDTO;
import dto.util.PaginationBuilder;
import model.DiscountType;
import java.util.Date;
import java.util.List;

public interface DiscountDAO {

    List<DiscountDTO> selectHistoryProductDiscounts(PaginationBuilder paginationBuilder);

    DiscountDTO getNowDiscountProduct();

    void insertEndDateDiscount(DiscountType discountType, Date endDateDiscount, int productId);

    int numberItemsDiscountHistory();

}
