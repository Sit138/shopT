package service;

import dao.BuyerDAO;
import dao.SaleDAO;
import model.Basket;
import dto.SaleDTO;
import dto.SoldProductDTO;
import entity.Buyer;
import entity.Sale;
import util.enums.SaleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleDAO saleDAO;

    @Autowired
    private BuyerDAO buyerDAO;

    @Override
    @Transactional
    public void save(String buyerName, Basket basket) {
        Buyer buyer = buyerDAO.getByName(buyerName);// TODO: Kirill а не нашел если? сохраним продажу без покупателя
        Sale sale = new Sale(buyer, basket);
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
    public List<SaleDTO> list() {
        return saleDAO.list();
    }

    @Override
    @Transactional
    public void updateState(int saleId, SaleState state) {
        saleDAO.updateState(saleId, state);
    }
}
