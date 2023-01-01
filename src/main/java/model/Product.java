package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

//creating class objects for Product
public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    public Product(int id, String name, double price,  int stock, int min, int max, ObservableList<Part> associatedParts){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        this.associatedParts = associatedParts;
    }
//returns product id
    public int getId(){
        return id;
    }

    //sets product id
    public void setId(int id){
        this.id = id;
    }

    //gets product name
    public String getName(){
        return name;
    }

    //sets product name
    public void setName(String name){
        this.name = name;
    }

    //gets product price
    public double getPrice(){
        return price;
    }

    //sets product price
    public void setPrice(double price){
        this.price = price;
    }

    //gets product minimum
    public int getMin(){
        return min;
    }

    //sets product minimum
    public void setMin(int min){
        this.min = min;
    }

    //gets product maximum

    public int getMax(){
        return max;
    }

    //sets product maximum
    public void setMax(int max){
        this.max = max;
    }

    //gets product stock
    public int getStock(){
        return stock;
    }

    //sets product stock
    public void setStock(int stock){
        this.stock = stock;
    }

    //gets associated parts in list
    public ObservableList<Part> getAssociatedParts(){
        return associatedParts;
    }


    // sets associated parts
    public void setAssociatedParts(ObservableList<Part> associatedParts){
        this.associatedParts = associatedParts;
    }

    //deletes associated part

    public void deleteAssociatedParts(Part thisAssociatedPart){
        associatedParts.remove(thisAssociatedPart);
    }

}
