package service;

import dto.BuyerDTO;

import java.math.BigDecimal;

public interface BuyerService {

    void save(BuyerDTO buyerDTO);

    BuyerDTO getByNameDTO(String name);

    void updateBalance(String buyerName, BigDecimal deposit);

    BigDecimal getBalanceByName(String buyerName);

    String getNameBySaleId(int saleId);

}
