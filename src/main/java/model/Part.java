package model;

import javafx.beans.property.*;

public abstract class Part {

    //Creating Instance Variables for Class Part
    private final IntegerProperty id;
    private final StringProperty name;
    private final IntegerProperty min;
    private final IntegerProperty max;
    private final DoubleProperty price;
    private final IntegerProperty stock;
    public Part(){
        this.id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.price = new SimpleDoubleProperty();
        this.min = new SimpleIntegerProperty();
        this.max = new SimpleIntegerProperty();
        this.stock = new SimpleIntegerProperty();

    }


    //getters

    public int getId(){
        return this.id.get();
    }
    public String getName(){
        return this.name.get();
    }
    public int getStock(){
        return this.stock.get();
    }
    public int getMin(){
        return this.min.get();
    }
    public int getMax(){
        return this.max.get();
    }
    public double getPrice(){
        return this.price.get();
    }

    public IntegerProperty partIDProperty(){
        return id;
    }
    public StringProperty partNameProperty(){
        return name;
    }
    public IntegerProperty partMinProperty(){
        return min;
    }
    public IntegerProperty partMaxProperty(){
        return max;
    }

    public DoubleProperty partPriceProperty(){
        return price;
    }
    public IntegerProperty partStockProperty() {
        return stock;
    }

    //setters
    public void setId(int id){
        this.id.set(id);
    }
    public void setName(String name){
        this.name.set(name);
    }

    public void setMin(int min){
        this.min.set(min);
    }
    public void setMax(int max){
        this.max.set(max);
    }
    public void setPrice(double price){
        this.price.set(price);
    }

    public void setStock(int stock){
        this.stock.set(stock);
    }

    public static String partValidator(String name, int min, int max, int stock, double price, String error){
        if(name == null){
            error = error + ("Name Field Blank.");
        }
        if(stock<1){
            error = error + ("Inventory can not be 0");
        }
        if (price < 1){
            error = error + ("Price can not be $0");

        }
        if (min > max){
            error = error + ("Min must be less than max");
        }
        if (stock < min || stock >max){
            error = error + ("Inventory must be between min and max");
        }
        return error;
    }

}
