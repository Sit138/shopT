package dao;

import dto.BuyerDTO;
import model.Buyer;

public interface BuyerDAO {

    void save(Buyer buyer);

    BuyerDTO getByNameDTO(String name);

    Buyer getByName(String name);
}
