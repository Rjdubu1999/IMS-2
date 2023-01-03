package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class InHouse extends Part{
    private final IntegerProperty machineId;
    public InHouse(){
        super();
        machineId = new SimpleIntegerProperty();
    }

    public int getMachineId(){
        return this.machineId.get();
    }

    public void setMachineId(int machineId){
        this.machineId.set(machineId);
    }

}
