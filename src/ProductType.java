import java.util.ArrayList;
import java.util.List;

class ProductType {
    private String name;
    private List<PropertyType> propertyTypes;

    public ProductType(String name) {
        this.name = name;
        this.propertyTypes = new ArrayList<>();
    }

    public void addPropertyType(PropertyType propertyType) {
        propertyTypes.add(propertyType);
    }

    public void removePropertyType(String propertyName) {
        PropertyType propertyToRemove = null;
        for (PropertyType propertyType : propertyTypes) {
            if (propertyType.getName().equals(propertyName)) {
                propertyToRemove = propertyType;
                break;
            }
        }
        if (propertyToRemove != null) {
            propertyTypes.remove(propertyToRemove);
            System.out.println("Property type removed: " + propertyName);
        } else {
            System.out.println("Property type not found: " + propertyName);
        }
    }


    public List<PropertyType> getPropertyTypes() {
        return propertyTypes;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }
}
