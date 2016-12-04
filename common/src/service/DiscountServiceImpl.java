package service;

import dao.DiscountDAO;
import dao.ProductDAO;
import dto.DiscountDTO;
import util.DiscountCalc;
import util.PaginationBuilder;
import model.Discount;
import model.enums.DiscountType;
import model.Product;
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
    public List<DiscountDTO> selectHistoryProductDiscounts(PaginationBuilder paginationBuilder) {
        return discountDAO.selectHistoryProductDiscounts(paginationBuilder);
    }

    @Override
    public DiscountDTO getNowAutoDiscountProduct() {
        return discountDAO.getNowAutoDiscountProduct();
    }

    @Override
    public int numberItemsDiscountHistory() {
        return discountDAO.numberItemsDiscountHistory();
    }

    @Override
    @Transactional
    public void insertEndDateDiscount(Date endDateDiscount, int productId) {
            discountDAO.insertEndDateDiscount(endDateDiscount, productId);
    }

    @Override
    public byte getValueByProductId(int id) {
        return discountDAO.getValueByProductId(id);
    }

    @Override
    @Transactional
    public void addProductDiscount(int productId, byte value, DiscountType type) {
        //product.addProductDiscont(discount);
        //productDAO.saveOrUpdate(product);
        Product product = productDAO.getProduct(productId);
        Discount discount = DiscountCalc.createDiscount(type, value);
        product.addProductDiscont(discount);
        productDAO.saveOrUpdate(product);
    }

}
