package util;

// TODO: Kirill а что оно билдит то? - :D нечто
public class Pagination {

    private int numberRowsOnPage;
    private int pageNumber;
    private int numberFirstSamplingElement;
    private int numberAllRows;

    public Pagination(){}

    public Pagination(int numberAllRows){
        this.numberRowsOnPage = 10;
        this.pageNumber = 0;
        this.numberFirstSamplingElement = pageNumber * numberRowsOnPage;
        this.numberAllRows = numberAllRows;
    }

    public int getNumberOfPages(){
        return this.numberAllRows / this.numberRowsOnPage;
    }

    /*public void updateNumberFirstSamplingElement(){
        this.numberFirstSamplingElement = pageNumber * numberRowsOnPage;
        // TODO: Kirill зачем апдейт с присвоением?
        // зачем я вызывать это должен из контроллера,
        // либо делай это при установке одного из множителей,
        // либо просто в гетере возвращай результат умножения
        //::Согласен -> Исправил. Любитель усложнить++
    }*/

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
        return pageNumber * numberRowsOnPage;
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
