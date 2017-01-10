package service;

import dao.DiscountDAO;
import dao.ProductDAO;
import dto.ProductDTO;
import dto.SoldProductDTO;
import entity.SoldProduct;
import model.Basket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Service
public class BasketServiceImpl implements BasketService {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private DiscountDAO discountDAO;

    @Override
    @Transactional(readOnly = true)
    public void addProduct(Basket basket, int productId, int amount) {
        if(basket == null){
            basket = new Basket();
        }
        ProductDTO productDTO = new ProductDTO(productDAO.getProduct(productId));
        byte discount = productDTO.isDiscounted() ? discountDAO.getValueByProductId(productId) : 0;
        if(isBasketProduct(basket, productDTO)){
            addToExistingProduct(basket, productDTO, amount);
        } else {
            SoldProductDTO basketProduct = new SoldProductDTO(productDTO, amount, discount);
            basket.getBasketProducts().add(basketProduct);
        }
    }

    @Override
    public void deleteProduct(Basket basket, int id, int amount) {
        for (SoldProductDTO product : basket.getBasketProducts()){
            if(product.getProductId() == id){
                if(product.getAmount() == amount){
                    deleteProduct(basket, id);
                    break;
                } else {
                    product.setAmount(product.getAmount() - amount);
                    break;
                }
            }
        }
    }

    @Override
    public int getCountProducts(Basket basket) {
        return basket.getBasketProducts().stream()
                .mapToInt(SoldProductDTO::getAmount).sum();
    }

    @Override
    public BigDecimal getCost(Basket basket) {
        BigDecimal totalSum = BigDecimal.ZERO;
        for (SoldProductDTO prod : basket.getBasketProducts()) {
            BigDecimal total = prod.getPriceWithDiscount().multiply(BigDecimal.valueOf(prod.getAmount()));
            totalSum = totalSum.add(total);
        }
        return totalSum;
    }

    @Override
    public Set<SoldProduct> getConversionToSoldProduct(Basket basket) {
        Set<SoldProduct> soldProducts = new HashSet<>();
        for(SoldProductDTO soldProductDTO : basket.getBasketProducts()){
            SoldProduct soldProduct = new SoldProduct(soldProductDTO);
            soldProducts.add(soldProduct);
        }
        return soldProducts;
    }

    @Override
    public void clear(Basket basket) {
        basket.getBasketProducts().clear();
    }

    private boolean isBasketProduct(Basket basket, ProductDTO product){
        return basket.getBasketProducts().stream()
                .anyMatch( p -> p.getProductId() == product.getId());
    }

    private void addToExistingProduct(Basket basket, ProductDTO product, int amount){
        basket.getBasketProducts().stream()
                .filter( p -> p.getProductId() == product.getId())
                .forEach( p -> p.setAmount(p.getAmount() + amount));
    }

    private void deleteProduct(Basket basket, int id){
        basket.getBasketProducts().removeIf( p -> p.getProductId() == id);
    }

}
