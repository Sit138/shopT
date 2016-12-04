package dao;

import dto.DiscountDTO;
import util.PaginationBuilder;
import java.util.Date;
import java.util.List;

public interface DiscountDAO {

    List<DiscountDTO> selectHistoryProductDiscounts(PaginationBuilder paginationBuilder);

    void insertEndDateDiscount(Date endDateDiscount, int productId);

    int numberItemsDiscountHistory();

    List<Integer> getIdWithoutDiscount();

    DiscountDTO getNowAutoDiscountProduct();

    byte getValueByProductId(int id);
}
