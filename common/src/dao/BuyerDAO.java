package dao;

import model.Buyer;
import java.math.BigDecimal;

public interface BuyerDAO {

    void save(Buyer buyer);

    Buyer getByName(String name);

    void updateBalance(String buyerName, BigDecimal deposit);

    BigDecimal getBalanceByName(String buyerName);

    String getNameBySaleId(int saleId);

}
