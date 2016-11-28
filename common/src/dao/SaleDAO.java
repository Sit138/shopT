package dao;


import dto.SaleDTO;
import model.Sale;

import java.util.List;

public interface SaleDAO {

    void save(Sale sale);

    List<SaleDTO> getByBuyerId(int buyerId);

    List getOrderInfo(int saleId);

}
