package service;

import dao.SaleDAO;
import dto.SaleDTO;
import dto.SoldProductDTO;
import model.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleDAO saleDAO;

    @Override
    @Transactional
    public void save(Sale sale) {
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
}
