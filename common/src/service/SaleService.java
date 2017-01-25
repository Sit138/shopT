package service;

import model.Basket;
import dto.SaleDTO;
import dto.SoldProductDTO;
import util.Pagination;
import util.enums.SaleState;
import java.util.List;

public interface SaleService {

    void save(String buyerName, Basket basket);

    List<SaleDTO> getByBuyerId(int buyerId);

    List<SoldProductDTO> getOrderInfo(int saleId);

    List<SaleDTO> list(Pagination pagination);

    void updateState(int saleId, SaleState state);

    void order(String buyerName, Basket basket);

    int countItemsSaleHistory();
}

