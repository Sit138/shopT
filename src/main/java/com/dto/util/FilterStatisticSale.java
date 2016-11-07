package com.dto.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class FilterStatisticSale extends PaginationBuilder {
    private Date fromDate;
    private Date toDate;

    public FilterStatisticSale(int numberAllRows){
        super(numberAllRows);
        this.fromDate = getDefaultDateFrom();
        this.toDate = getDefaultDateTo();
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    private Date getDefaultDateFrom(){
        // TODO: Kirill Calendar from  = GregorianCalendar.getInstance();
        Calendar from = new GregorianCalendar();
        from.add(Calendar.DAY_OF_WEEK, -7);
        return from.getTime();
    }

    private Date getDefaultDateTo(){
        return new GregorianCalendar().getTime();
    }
}
