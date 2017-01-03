package service;

import entity.SoldProduct;
import model.Basket;

import java.math.BigDecimal;
import java.util.Set;

public interface BasketService {

    void addProduct(Basket basket, int productId, int amount);

    void deleteProduct(Basket basket, int id, int amount);

    int getCountProducts(Basket basket);

    BigDecimal getCost(Basket basket);

    Set<SoldProduct> getConversionToSoldProduct(Basket basket);

    void clear(Basket basket);
}
