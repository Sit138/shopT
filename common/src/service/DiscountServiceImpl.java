package service;

import dao.DiscountDAO;
import dao.ProductDAO;
import dto.DiscountDTO;
import util.DiscountCalc;
import util.Pagination;
import entity.Discount;
import util.enums.DiscountType;
import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountDAO discountDAO;

    @Autowired
    private ProductDAO productDAO;

    @Override
    public List<DiscountDTO> selectHistoryProductDiscounts(Pagination pagination) {
        return discountDAO.getHistoryProductDiscounts(pagination);
    }

    @Override
    public DiscountDTO getNowAutoDiscountProduct() {
        return discountDAO.getNowAutoDiscountProduct();
    }

    @Override
    public int numberItemsDiscountHistory() {
        return discountDAO.countItemsDiscountHistory();
    }

    @Override
    @Transactional
    public void insertEndDateDiscount(Date endDateDiscount, int productId) {
        Product product = productDAO.getProduct(productId);
        product.setDiscounted(false);
        productDAO.saveOrUpdate(product);
        discountDAO.insertEndDateDiscount(endDateDiscount, productId);
    }

    @Override
    public byte getValueByProductId(int id) {
        return discountDAO.getValueByProductId(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addProductDiscount(int productId, byte value, DiscountType type) {
        Product product = productDAO.getProduct(productId);
        Discount discount = DiscountCalc.createDiscount(type, value);
        product.addProductDiscount(discount);
        product.setDiscounted(true);
        productDAO.saveOrUpdate(product);
    }

}
