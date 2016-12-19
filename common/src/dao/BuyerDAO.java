package dao;

import dto.BuyerDTO;
import model.Buyer;
import java.math.BigDecimal;

public interface BuyerDAO {

    void save(Buyer buyer);

    // TODO: Kirill выглядит фигово, не так ли? значит что то тут не так пошло
    BuyerDTO getByNameDTO(String name);
    Buyer getByName(String name);
    //----------------------------------

    void updateBalance(String buyerName, BigDecimal deposit);

    BigDecimal getBalanceByName(String buyerName);

    String getNameBySaleId(int saleId);

}
