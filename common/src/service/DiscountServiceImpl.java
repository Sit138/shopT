package service;

import dao.DiscountDAO;
import dao.ProductDAO;
import dto.DiscountDTO;
import dto.util.PaginationBuilder;
import model.Discount;
import model.DiscountType;
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
    public DiscountDTO getNowDiscountProduct() {
        return discountDAO.getNowDiscountProduct();
    }

    @Override
    public int numberItemsDiscountHistory() {
        return discountDAO.numberItemsDiscountHistory();
    }

    @Override
    @Transactional
    public void insertEndDateDiscount(DiscountType discountType, Date endDateDiscount, int productId) {
        discountDAO.insertEndDateDiscount(discountType, endDateDiscount, productId);
    }

    @Override
    @Transactional
    public void insertProductDiscount() {
        Product productDiscount = productDAO.getRandomProduct();
        Discount discount = createDiscount();
        productDiscount.addProductDiscont(discount);
        productDAO.saveOrUpdate(productDiscount);
    }

    private Discount createDiscount(){
        if(getNowDiscountProduct() != null){
            removeAutoDiscountNow();
        }
        Date currentDate = new Date();
        int min = 5; int max = 15;//нижнее/верхнее значение процентов скидки
        double newDiscount = min + (Math.random() * (max - min) + 1);
        Discount discount = new Discount(newDiscount, currentDate, DiscountType.Auto);
        return discount;
    }

    private void removeAutoDiscountNow(){
        Date currentDate = new Date();
        int productIdNowDiscount = getNowDiscountProduct().getProductId();
        insertEndDateDiscount(DiscountType.Auto, currentDate, productIdNowDiscount);
    }
}
