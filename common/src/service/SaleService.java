package service;

import model.Basket;
import dto.SaleDTO;
import dto.SoldProductDTO;
import util.enums.SaleState;

import java.util.List;

public interface SaleService {

    void save(String buyerName, Basket basket);

    List<SaleDTO> getByBuyerId(int buyerId);

    List<SoldProductDTO> getOrderInfo(int saleId);

    List<SaleDTO> list();

    void updateState(int saleId, SaleState state);
}

