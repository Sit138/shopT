package dao;

import dto.BuyerDTO;
import entity.Buyer;
import java.math.BigDecimal;
import java.util.List;

public interface BuyerDAO extends GeneralDAO<Buyer> {

    //void save(Buyer buyer);

    Buyer getByName(String name);

    Buyer getById(int id);

    List<BuyerDTO> list();

    void addToBalance(int id, BigDecimal value);

    BigDecimal getBalanceById(int id);

    String getNameBySaleId(int saleId);

}
