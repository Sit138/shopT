package com.dto.util;

import java.util.Date;

// TODO: Kirill а что оно билдит то?
public class PaginationBuilder {

    private int numberRowsOnPage;
    private int pageNumber;
    private int numberFirstSamplingElement;
    private int numberAllRows;

    public PaginationBuilder(){}

    public PaginationBuilder(int numberAllRows){
        this.numberRowsOnPage = 10;
        this.pageNumber = 0;
        this.numberFirstSamplingElement = pageNumber * numberRowsOnPage;
        this.numberAllRows = numberAllRows;
    }

    public int getNumberOfPages(){
        return this.numberAllRows / this.numberRowsOnPage;
    }

    public void updateNumberFirstSamplingElement(){
        this.numberFirstSamplingElement = pageNumber * numberRowsOnPage;
    }

    public int getNumberRowsOnPage() {
        return numberRowsOnPage;
    }

    public void setNumberRowsOnPage(int numberRowsOnPage) {
        this.numberRowsOnPage = numberRowsOnPage;
    }


    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getNumberFirstSamplingElement() {
        return numberFirstSamplingElement;
    }

    public void setNumberFirstSamplingElement(int numberFirstSamplingElement) {
        this.numberFirstSamplingElement = numberFirstSamplingElement;
    }

    public int getNumberAllRows() {
        return numberAllRows;
    }

    public void setNumberAllRows(int numberAllRows) {
        this.numberAllRows = numberAllRows;
    }
}
