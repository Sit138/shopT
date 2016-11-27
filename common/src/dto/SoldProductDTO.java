package dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class SoldProductDTO {

    private String name;

    private int amount;

    private BigDecimal price;

    private double discount;

    private int productId;

    public SoldProductDTO(){}

    public SoldProductDTO(ProductDTO productDTO, int amount, double discount){
        this.productId = productDTO.getId();
        this.name = productDTO.getName();
        this.price = productDTO.getPrice();
        this.amount = amount;
        this.discount = discount;
    }

    public BigDecimal getPriceWithDiscount(){
        return new BigDecimal(this.getPrice().subtract(getDiscountMoney()).toString());
    }

    public BigDecimal getDiscountMoney(){
        BigDecimal disc = new BigDecimal(this.getDiscount() * 0.01);
        return this.getPrice().multiply(disc);
    }

}
