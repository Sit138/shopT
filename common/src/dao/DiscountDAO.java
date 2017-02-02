package dao;

import dto.DiscountDTO;
import util.Pagination;
import java.util.Date;
import java.util.List;

public interface DiscountDAO {

    List<DiscountDTO> getHistoryProductDiscounts(Date dateFrom, Date dateTo, Pagination pagination);

    void insertEndDateDiscount(Date endDateDiscount, int productId);

    int countItemsDiscountHistory();

    DiscountDTO getNowAutoDiscountProduct();

    byte getValueByProductId(int id);
}
