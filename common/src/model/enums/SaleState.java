package model.enums;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

public enum SaleState {

    SENT, CANCELED, CONFIRMED;

    public static final EnumSet<SaleState> saleStates = EnumSet.of(SENT, CANCELED, CONFIRMED);

    public static List<SaleState> list(){
        return Arrays.asList(values());
    }// TODO: Kirill values() копирует значения каждый раз при обращении::вынес в статик переменную

    public static SaleState parse(String state){
        SaleState st = null;
        for (SaleState s : list()){// TODO: Kirill какой тест?::не знаю))исправил
            if(s.name().equals(state)){
                st = s;
                break;
            }
        }
        return st;
    }

}
