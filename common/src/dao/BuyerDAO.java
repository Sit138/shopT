package dao;

import dto.BuyerDTO;
import model.Buyer;

import java.math.BigDecimal;

public interface BuyerDAO {

    void save(Buyer buyer);

    BuyerDTO getByNameDTO(String name);

    Buyer getByName(String name);

    void updateBalance(String buyerName, BigDecimal deposit);

    BigDecimal getBalanceByName(String buyerName);

}
