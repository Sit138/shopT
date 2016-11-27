package dao;

import dto.DiscountDTO;
import util.PaginationBuilder;
import model.enums.DiscountType;
import java.util.Date;
import java.util.List;

public interface DiscountDAO {

    List<DiscountDTO> selectHistoryProductDiscounts(PaginationBuilder paginationBuilder);

    DiscountDTO getNowDiscountProduct();

    void insertEndDateDiscount(DiscountType discountType, Date endDateDiscount, int productId);

    int numberItemsDiscountHistory();

}
