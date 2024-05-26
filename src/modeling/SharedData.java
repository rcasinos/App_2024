/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeling;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Charge;

public class SharedData {
    private static SharedData instance;
    private ObservableList<Charge> charges;
    private Charge activeCharge;
    
    private int id;
    private String category;
    private double cost;
    private double units;

    private SharedData() {
        charges = FXCollections.observableArrayList();
    }

    public static synchronized SharedData getInstance() {
        if (instance == null) {
            instance = new SharedData();
        }
        return instance;
    }

    public ObservableList<Charge> getCharges() {
        return charges;
    }

    public void addCharge(Charge charge) {
        charges.add(charge);
    }
    
    public void removeCharge(Charge charge) {
    charges.remove(charge);
    }
    
    public void setActiveCharge(Charge activeCharge) {
        this.activeCharge = activeCharge;       
    }
    
    public Charge getActiveCharge() {
        return activeCharge;
    }
    
    // Getters y setters
    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public double getCost() {
        return cost;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
    
    public double getUnits() {
        return units;
    }
    
    public void setUnits(double units) {
        this.units = units;
    }    
}
