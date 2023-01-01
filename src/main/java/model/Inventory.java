package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

public class Inventory{

    //The Inventory class holds all the products and parts and
    // the methods need to search, add, update and delete parts
    // and products.

    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part newPart){
        allParts.add(newPart);
    }
    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }
    public static Part lookupPart(int PartID){
        Part searchPartID = null;
        for(Part part : getAllParts()){
            if(part.getId() == PartID)
                searchPartID = part;

        }
    return searchPartID;
    }

    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }

    public static Product lookupProduct(int productID){
        Product searchProductID = null;
        for(Product product : Inventory.getAllProducts()){
            //define these in product class
            if(product.getId() == productID){
                searchProductID = product;
            }
        }
        return searchProductID;
    }

    public static ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> returnedPart = FXCollections.observableArrayList();
        for(Part part : allParts){
            if(part.getName().toLowerCase().contains(partName.toLowerCase())){
                returnedPart.add(part);
            }
        }
        return returnedPart;
    }

    public static ObservableList<Product> lookupProduct(String productName){
    ObservableList<Product> returnedProduct = FXCollections.observableArrayList();{
        for(Product product : allProducts){
            if(product.getName().toLowerCase().contains(productName.toLowerCase())){
                returnedProduct.add(product);
            }

        }
        return returnedProduct;
        }

    }

    public static void updatePart(int id, Part selectedPart){
        int index = -1;
        for(Part part : getAllParts()){
            index++;
            if(part.getId() == id){
                getAllParts().set(index,selectedPart);
            }
        }
    }

    public static void updateProduct(int id, Product selectedProduct){
        int index = -1;
        for(Product product : getAllProducts()){
            index++;
            if(product.getId() == id){
                getAllProducts().set(index, selectedProduct);
            }
        }
    }

    public static void deletePart(Part selectedPart) {
        if (allProducts.size() > 0) {
            for (int i = 0; i < allProducts.size(); i++) {
                if (allProducts.get(i).getAssociatedParts().contains(selectedPart)) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning!");
                    alert.setContentText("Part has association with a Product");
                    alert.showAndWait();

                } else {
                    allParts.remove(selectedPart);
                }
            }
        } else {
            allParts.remove(selectedPart);
        }
    }

    public static void deleteProduct(Part selectedProduct){
        allProducts.remove(selectedProduct);
    }



}

