package service;

import dao.BuyerDAO;
import dto.BuyerDTO;
import model.Buyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private BuyerDAO buyerDAO;

    @Override
    public void save(Buyer buyer) {
        buyerDAO.save(buyer);
    }

    @Override
    public void updateBalance(String buyerName, BigDecimal deposit) {
        buyerDAO.updateBalance(buyerName, deposit);
    }

    @Override
    public BigDecimal getBalanceByName(String buyerName) {
        return buyerDAO.getBalanceByName(buyerName);
    }

    @Override
    public BuyerDTO getByNameDTO(String name) {
        return buyerDAO.getByNameDTO(name);
    }
}
