import java.util.ArrayList;
import java.util.List;

class Product {
    private ProductType type;
    private List<Property> properties;
    private String name;
    public Product(ProductType type, String name) { // Modify constructor to accept name parameter
        this.type = type;
        this.name = name; // Set the name
        this.properties = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ProductType getType() {
        return type;
    }

    public void setProperty(String propertyName, Object value) {
        for (PropertyType propertyType : type.getPropertyTypes()) {
            if (propertyType.getName().equals(propertyName)) {
                if (propertyType.getType().isInstance(value)) {
                    properties.add(new Property(propertyType, value));
                    return;
                } else {
                    throw new IllegalArgumentException("Invalid value type for property: " + propertyName);
                }
            }
        }
        throw new IllegalArgumentException("Property not found: " + propertyName);
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void removeProperty(String propertyName) {
        for (int i = 0; i < properties.size(); i++) {
            Property property = properties.get(i);
            if (property.getType().getName().equals(propertyName)) {
                properties.remove(i);
                return;
            }
        }
        throw new IllegalArgumentException("Property not found: " + propertyName);
    }

    public Object getPropertyValue(String propertyName){
        for(Property property:properties){
            if(property.getType().getName().equals(propertyName)){
                return property.getValue();
            }
        }
        return null;
    }

    public boolean hasProperty(String propertyName){
        for(Property property:properties){
            if(property.getType().getName().equals(propertyName)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Product: " +
                type.toString() +
                ", properties: " + properties.toString();
    }

}