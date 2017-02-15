package service;

import dto.BuyerDTO;

import java.math.BigDecimal;
import java.util.List;

public interface BuyerService {

    void save(BuyerDTO buyerDTO);

    BuyerDTO getDTOByName(String name);

    List<BuyerDTO> list();

    void addToBalance(int id, BigDecimal value);

    BigDecimal getBalanceById(int id);

    String getNameBySaleId(int saleId);

}
