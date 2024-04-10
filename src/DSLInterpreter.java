import java.util.HashMap;
import java.util.Map;

public class DSLInterpreter {
    ProductAttributes productAttributes;
    Store store;
    public DSLInterpreter(Store store){
        this.productAttributes = new ProductAttributes();
        this.store = store;

    }

    public void interpretCommand(String command) {
        String[] tokens = command.split(" ");

        switch (tokens[0]) {
            case "NewProductType":
                String entityType = tokens[1];
                productAttributes.createProductType(entityType);
                break;
            case "NewPropertyType":
                String propertyType = tokens[1];
                String propertyClass = tokens[2];
                productAttributes.createPropertyType(propertyType, getTypeClass(propertyClass));
                break;
            case "AddPropType":
                String entityTypeName = tokens[1];
                String propertyName = tokens[2];
                productAttributes.addPropertyTypeToProductType(entityTypeName,propertyName);
                break;
            case "NewProduct":
                String productName = tokens[1];
                String productTypeName = tokens[2];
                store.addProduct(productAttributes.createProduct(productName,productTypeName));
                break;
            case "AddNewProperty":
                String entityNameNew = tokens[1];
                String propertyNameNew = tokens[2];
                String propertyValue = tokens[3];
                productAttributes.setPropertyForEntity(entityNameNew,propertyNameNew,parseNumberOrString(propertyValue));
                break;
            case "RemovePropType":
                String propertyTypeName =tokens [1];
                String propertyTypeToRemove = tokens[2];
                productAttributes.removePropertyTypeFromProductType(propertyTypeName,propertyTypeToRemove);
                break;
            case "AddRule":
                String productType =tokens [1];
                String productAttribute = tokens[2];
                String rule = "";
                for (int i = 3; i < tokens.length; i++) {
                    rule += tokens[i];
                    if (i < tokens.length - 1) {
                        rule += " "; // Add space between tokens
                    }
                }
                if (rule.startsWith("(") && rule.endsWith(")")) {
                    rule = rule.substring(1, rule.length() - 1);
                }
                productAttributes.addRuleToEntity(productType,productAttribute,rule);
                break;
            case "ApplyRule":
                String productNamePrice = tokens[1];
                String productPrice =tokens[2];
                productAttributes.applyRulePrice(productNamePrice,productPrice);
                break;
            default:
                System.out.println("Invalid command: " + command);
        }
    }

    private static Object parseNumberOrString(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e1) {
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e2) {
                // Return the original string if it's neither an integer nor a double
                return input;
            }
        }
    }
    private Class<?> getTypeClass(String typeName) {
        switch (typeName) {
            case "String":
                return String.class;
            case "Double":
                return Double.class;
            case "Integer":
                return Integer.class;

            default:
                throw new IllegalArgumentException("Unknown property type: " + typeName);
        }
    }


}
