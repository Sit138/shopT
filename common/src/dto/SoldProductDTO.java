package dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter @Setter
public class SoldProductDTO {

    private String name;

    private int amount;

    private BigDecimal price;

    private byte discount;

    private int productId;

    public SoldProductDTO(){}

    public SoldProductDTO(ProductDTO productDTO, int amount, byte discount){
        this.productId = productDTO.getId();
        this.name = productDTO.getName();
        this.price = productDTO.getPrice();
        this.amount = amount;
        this.discount = discount;
    }

    public BigDecimal getPriceWithDiscount(){
        return (new BigDecimal(this.getPrice().subtract(getDiscountMoney()).toString()))
                .setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getDiscountMoney(){
        BigDecimal disc = (new BigDecimal((this.getDiscount() * 0.01))).setScale(2, BigDecimal.ROUND_HALF_UP);
        return this.getPrice().multiply(disc);
    }

}
