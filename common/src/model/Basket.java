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

    /*public void addProduct(ProductDTO productDTO, int amount, byte discount){
        if(isBasketProduct(productDTO)){
            addToExistingProduct(productDTO, amount);
        } else {
            SoldProductDTO basketProduct = new SoldProductDTO(productDTO, amount, discount);
            basketProducts.add(basketProduct);
        }
    }*/

    /*private boolean isBasketProduct(ProductDTO product){
        return basketProducts.stream()
                .anyMatch( p -> p.getProductId() == product.getId());
    }*/

    /*private void addToExistingProduct(ProductDTO product, int amount){
        basketProducts.stream()
                .filter( p -> p.getProductId() == product.getId())
                .forEach( p -> p.setAmount(p.getAmount() + amount));
    }*/

    /*private void deleteProduct(int id){
        basketProducts.removeIf( p -> p.getProductId() == id);
    }

    public void deleteProduct(int id, int amount){
        for (SoldProductDTO product : basketProducts){
            if(product.getProductId() == id){
                if(product.getAmount() == amount){
                    deleteProduct(id);
                    break;
                } else {
                    product.setAmount(product.getAmount() - amount);
                    break;
                }
            }
        }
    }*/

    /*public int getCountProducts(){
        return basketProducts.stream()
                .mapToInt(SoldProductDTO::getAmount).sum();
    }*/

    /*public BigDecimal getCost(){
        BigDecimal totalSum = BigDecimal.ZERO;
        for (SoldProductDTO prod : basketProducts) {
            BigDecimal total = prod.getPriceWithDiscount().multiply(BigDecimal.valueOf(prod.getAmount()));
            totalSum = totalSum.add(total);
        }
        return totalSum;
    }*/

    /*public Set<SoldProduct> getConversionToSoldProduct(){
        Set<SoldProduct> soldProducts = new HashSet<>();
        for(SoldProductDTO soldProductDTO : basketProducts){
            SoldProduct soldProduct = new SoldProduct(soldProductDTO);
            soldProducts.add(soldProduct);
        }
        return soldProducts;
    }

    public void clear(){
        basketProducts.clear();
    }*/

}
