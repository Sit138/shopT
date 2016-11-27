package service;

import dao.BuyerDAO;
import dto.BuyerDTO;
import model.Buyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Buyer getByName(String name) {
        return buyerDAO.getByName(name);
    }

    @Override
    public BuyerDTO getByNameDTO(String name) {
        return buyerDAO.getByNameDTO(name);
    }
}
