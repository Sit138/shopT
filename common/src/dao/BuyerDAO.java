package dao;

import entity.Buyer;
import java.math.BigDecimal;

public interface BuyerDAO {

    void save(Buyer buyer);

    Buyer getByName(String name);

    void addToBalance(int id, BigDecimal value);

    BigDecimal getBalanceById(int id);

    String getNameBySaleId(int saleId);

}
