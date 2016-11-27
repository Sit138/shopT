package service;

import dto.SaleDTO;
import dto.SoldProductDTO;
import model.Sale;

import java.util.List;

public interface SaleService {

    void save(Sale sale);

    List<SaleDTO> getByBuyerId(int buyerId);

    List<SoldProductDTO> getOrderInfo(int saleId);
}

