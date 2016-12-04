package model.enums;

import java.util.Arrays;
import java.util.List;

public enum SaleState {

    SENT, CANCELED, CONFIRMED;

    public static List<SaleState> list(){
        return Arrays.asList(values());
    }

    public static SaleState parse(String state){
        SaleState st = null;
        for (SaleState test : list()){
            if(test.name().equals(state)){
                st = test;
                break;
            }
        }
        return st;
    }

}
