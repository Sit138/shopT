package service;

import dao.BuyerDAO;
import dto.BuyerDTO;
import entity.Buyer;
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
    public void save(BuyerDTO buyerDTO) {
        Buyer buyer = new Buyer(buyerDTO);
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
    public String getNameBySaleId(int saleId) {
        return buyerDAO.getNameBySaleId(saleId);
    }

    @Override
    public BuyerDTO getByNameDTO(String name) {
        Buyer buyerEntity = buyerDAO.getByName(name);
        return new BuyerDTO(buyerEntity);
    }
}
