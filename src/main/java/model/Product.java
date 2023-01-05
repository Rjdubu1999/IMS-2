package model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

//creating class objects for Product
public class Product {
    private final IntegerProperty id;
    private final StringProperty name;
    private final DoubleProperty price;
    private final IntegerProperty stock;
    private final IntegerProperty min;
    private final IntegerProperty max;
    private static ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private String machineID;

    public Product() {
        id = new SimpleIntegerProperty();
        name = new SimpleStringProperty();
        price = new SimpleDoubleProperty();
        stock = new SimpleIntegerProperty();
        min = new SimpleIntegerProperty();
        max = new SimpleIntegerProperty();

    }

    public IntegerProperty productIDProperty() {
        return id;
    }

    public IntegerProperty productInventoryProperty() {
        return stock;
    }

    public IntegerProperty productMinProperty() {
        return min;
    }

    public IntegerProperty productMaxProperty() {
        return max;

    }

    public StringProperty productNameProperty() {
        return name;
    }

    public DoubleProperty productPriceProperty() {
        return price;
    }

    //returns product id
    public int getId() {
        return this.id.get();
    }

    //sets product id
    public void setId(int id) {
        this.id.set(id);
    }

    //gets product name
    public String getName() {
        return this.name.get();
    }

    //sets product name
    public void setName(String name) {
        this.name.set(name);
    }

    //gets product price
    public double getPrice() {
        return this.price.get();
    }

    //sets product price
    public void setPrice(double price) {
        this.price.set(price);
    }

    //gets product minimum
    public int getMin() {
        return this.min.get();
    }

    //sets product minimum
    public void setMin(int min) {
        this.min.set(min);
    }

    //gets product maximum

    public int getMax() {
        return this.max.get();
    }

    //sets product maximum
    public void setMax(int max) {
        this.max.set(max);
    }

    //gets product stock
    public int getStock() {
        return this.stock.get();
    }

    //sets product stock
    public void setStock(int stock) {
        this.stock.set(stock);
    }

    //gets associated parts in list
    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }


    // sets associated parts
    public void setAssociatedParts(ObservableList<Part> associatedParts) {
        this.associatedParts = associatedParts;
    }

    //deletes associated part

    public void deleteAssociatedParts(Part thisAssociatedPart) {
        associatedParts.remove(thisAssociatedPart);
    }


    public static String productValidator(String name,  int min, int max, int stock, double price, ObservableList<Part> associatedParts, String error) {
    double partsSum = 0.00;
    for (int i = 0; i < associatedParts.size(); i++){
        partsSum = partsSum + associatedParts.get(i).getPrice();
        }
    if(name == null){
        error = error + "Blank name field.";
    }
    if(min < 0 ){
        error = error + "Inventory can not be less than 0";
    }
    if( min > max){
        error = error + "Min must be less than max";
    }
    if(stock < min || stock > max){
        error = error + "Inventory must be between min and max";
    }
    if(price <= 0){
        error = error + "Price must be greater than 0.00";
    }
   // if(associatedParts.size() < 1){
    //    error = error + "Product must have 1 or more parts";
   // }
    if(partsSum > price){
        error = error + "Product's price must be higher than the cost of all parts.";
    }
    return error;
    }


}