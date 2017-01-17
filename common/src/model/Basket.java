package model;

import dto.SoldProductDTO;
import lombok.Getter;
import lombok.Setter;
import java.util.*;

@Getter @Setter
public class Basket {

    private List<SoldProductDTO> basketProducts = new ArrayList<>();

    public Basket(){}

    public Basket(List<SoldProductDTO> basketProducts){
        this.basketProducts = basketProducts;
    }

}
