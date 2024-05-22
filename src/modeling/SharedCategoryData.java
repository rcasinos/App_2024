/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeling;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Category;

public class SharedCategoryData {
    private static SharedCategoryData instance;
    private ObservableList<Category> categories;

    private SharedCategoryData() {
        categories = FXCollections.observableArrayList();
    }

    public static synchronized SharedCategoryData getInstance() {
        if (instance == null) {
            instance = new SharedCategoryData();
        }
        return instance;
    }

    public ObservableList<Category> getCategories() {
        return categories;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }
}