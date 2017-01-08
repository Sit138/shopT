package dao;

import entity.Buyer;
import java.math.BigDecimal;

public interface BuyerDAO {

    void save(Buyer buyer);

    Buyer getByName(String name);

    void addToBalance(String buyerName, BigDecimal value);

    BigDecimal getBalanceByName(String buyerName);

    String getNameBySaleId(int saleId);

}
