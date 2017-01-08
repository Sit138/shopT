package util;

import entity.Discount;
import util.enums.DiscountType;
import java.util.Date;

public class DiscountCalc {

    public static byte getRandomValueDiscount(int min, int max){
        return (byte) (min + (Math.random() * (max - min) + 1));
    }

    public static Discount createDiscount(DiscountType type, byte value){
        Date currentDate = new Date();
        Discount discount = new Discount(value, currentDate, type);
        return discount;
    }

}
