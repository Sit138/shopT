package service;

import dao.BuyerDAO;
import dao.SaleDAO;
import model.Basket;
import dto.SaleDTO;
import dto.SoldProductDTO;
import entity.Buyer;
import entity.Sale;
import util.Pagination;
import util.enums.SaleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleDAO saleDAO;

    @Autowired
    private BuyerDAO buyerDAO;

    @Autowired
    private BasketService basketService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(String buyerName, Basket basket) {
        Buyer buyer = buyerDAO.getByName(buyerName);
        if (buyer == null) return;
        Sale sale = new Sale();
        sale.setDate(new Date());
        sale.setPositions(basketService.getCountProducts(basket));
        sale.setTotalSum(basketService.getCost(basket));
        sale.setState(SaleState.SENT);
        sale.setSoldProducts(basketService.getConversionToSoldProduct(basket));
        sale.setBuyer(buyer);
        saleDAO.save(sale);
    }

    @Override
    public List<SaleDTO> getByBuyerId(int buyerId) {
        return saleDAO.getByBuyerId(buyerId);
    }

    @Override
    public List<SoldProductDTO> getOrderInfo(int saleId) {
        return saleDAO.getOrderInfo(saleId);
    }

    @Override
    public List<SaleDTO> list(Pagination pagination) {
        return saleDAO.list(pagination);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateState(int saleId, SaleState state) {
        saleDAO.updateState(saleId, state);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void order(String buyerName, Basket basket) {
        try {
            save(buyerName, basket);
            Buyer buyer = buyerDAO.getByName(buyerName);
            buyerDAO.addToBalance(buyer.getId(), basketService.getCost(basket).negate());
        } catch (Exception e){
            return;
        }
        basketService.clear(basket);
    }

    @Override
    public int countItemsSaleHistory() {
        return saleDAO.countItemsSaleHistory();
    }
}
