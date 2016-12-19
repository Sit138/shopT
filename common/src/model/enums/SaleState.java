package model.enums;

import java.util.Arrays;
import java.util.List;

public enum SaleState {

    SENT, CANCELED, CONFIRMED;

    public static List<SaleState> list(){
        return Arrays.asList(values());
    }// TODO: Kirill values() копирует значения каждый раз при обращении

    public static SaleState parse(String state){
        SaleState st = null;
        for (SaleState test : list()){// TODO: Kirill какой тест?
            if(test.name().equals(state)){
                st = test;
                break;
            }
        }
        return st;
    }

}
