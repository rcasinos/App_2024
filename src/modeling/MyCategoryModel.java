package modeling;

public class MyCategoryModel {
    
    private String name;
    private String description;

    public MyCategoryModel(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    // Getters y setters si es necesario
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String toString() {
        return name; // O cualquier otra información que desees mostrar en el ListView
    }
}

