package service;

import dto.BuyerDTO;
import model.Buyer;

public interface BuyerService {

    void save(Buyer buyer);

    BuyerDTO getByNameDTO(String name);

    Buyer getByName(String name);

}
