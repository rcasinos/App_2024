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
}
