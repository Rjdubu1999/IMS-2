package model;

import javafx.beans.property.StringProperty;

public class Outsourced extends Part {
    private StringProperty companyName;
    public Outsourced() {
        super();
    }
    public String getCompanyName(){
        return this.companyName.get();
    }

    public void setCompanyName(String companyName){
        this.companyName.set(companyName);
    }
}
