package service;

import dto.BuyerDTO;

import java.math.BigDecimal;

public interface BuyerService {

    void save(BuyerDTO buyerDTO);

    BuyerDTO getDTOByName(String name);

    void addToBalance(int id, BigDecimal value);

    BigDecimal getBalanceById(int id);

    String getNameBySaleId(int saleId);

}
