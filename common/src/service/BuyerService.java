package service;

import dto.BuyerDTO;
import model.Buyer;

import java.math.BigDecimal;

public interface BuyerService {

    void save(Buyer buyer);

    BuyerDTO getByNameDTO(String name);

    void updateBalance(String buyerName, BigDecimal deposit);

    BigDecimal getBalanceByName(String buyerName);

}
