import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductAttributes {

    private Map<String, ProductType> productTypes;
    private Map<String, PropertyType> propertyTypes;
    private List<Product> products;
    private Map<String, String> priceRules;

    public ProductAttributes() {
        this.productTypes = new HashMap<>();
        this.propertyTypes = new HashMap<>();
        this.priceRules = new HashMap<>();
        this.products =new ArrayList<>();
    }

    public void createProductType(String typeName) {
        if (!productTypes.containsKey(typeName)) {
            ProductType newType = new ProductType(typeName);
            productTypes.put(typeName, newType);
            System.out.println("Product type created: " + typeName);
        } else {
            System.out.println("Product type already exists: " + typeName);
        }
    }

    public void createPropertyType(String typeName, Class<?> type) {
        if (!propertyTypes.containsKey(typeName)) {
            PropertyType newType = new PropertyType(typeName, type);
            propertyTypes.put(typeName, newType);
            System.out.println("Property type created: " + typeName + ", " + type);
        } else {
            System.out.println("Property type already exists: " + typeName);
        }
    }

    public void addPropertyTypeToProductType(String productTypeName, String propertyTypeName) {
        ProductType productType = productTypes.get(productTypeName);
        if (productType != null) {
            PropertyType propertyType = propertyTypes.get(propertyTypeName);
            if (propertyType != null) {
                productType.addPropertyType(propertyType);
                System.out.println("Added property type " + propertyTypeName + " to product type " + productTypeName);
            } else {
                System.out.println("Property type not found: " + propertyTypeName);
            }
        } else {
            System.out.println("Product type not found: " + productTypeName);
        }
    }

    public Product createProduct(String productName, String productTypeName) {
        ProductType productType = productTypes.get(productTypeName);
        if (productType != null) {
            Product newProduct = new Product(productType,productName);
            products.add(newProduct);
            System.out.println("Product created: " + productName);
            return newProduct;
        } else {
            System.out.println("Product type not found: " + productTypeName);
            return null;
        }
    }

    public void setPropertyForEntity(String entityName, String propertyName, Object value) {
        Product product = getProductByName(entityName);
        if (product != null) {
            product.setProperty(propertyName, value);
            System.out.println("Property set for entity " + entityName + ": " + propertyName + " = " + value);
        } else {
            System.out.println("Entity not found: " + entityName);
        }
    }

    public Product getProductByName(String entityName) {
        for (Product product : products) {
            if (product.getName().equals(entityName)) {
                return product;
            }
        }
        return null;
    }

    public void removePropertyTypeFromProductType(String productTypeName, String propertyTypeName) {
        ProductType productType = productTypes.get(productTypeName);
        if (productType != null) {
            List<PropertyType> propertyType = productType.getPropertyTypes();
            for(PropertyType property: propertyType){
                if(property.getName().equals(propertyTypeName)){
                    productType.removePropertyType(propertyTypeName);
                    break;
                }
            }

        } else {
            System.out.println("Product type not found: " + productTypeName);
        }
    }

    public void addRuleToEntity(String productTypeName, String ruleName, String ruleDescription) {
        String key = productTypeName + "_" + ruleName;
        if (!priceRules.containsKey(key)) {
            priceRules.put(key, ruleDescription);
            System.out.println("Rule added to entity " + productTypeName + ": " + ruleName + " = " + ruleDescription);
        } else {
            System.out.println("Rule already exists for entity " + productTypeName + ": " + ruleName);
        }
    }
    public void applyRulePrice(String productName, String rule) {
        Product product = getProductByName(productName);
        String productTypeName=product.getType().getName();
        if (product != null) {
           PriceCalculator priceCalculator = new PriceCalculator();
           String key = productTypeName + "_" + rule;
           if(priceRules.get(key)!=null) {
               double price = priceCalculator.computePrice(product, priceRules.get(key));
               System.out.println(price);

               this.createPropertyType(rule,Double.class);
               this.addPropertyTypeToProductType(productTypeName,rule);
               this.setPropertyForEntity(productName,rule,price);

           }
        } else {
            System.out.println("Product not found: " + productName);
        }
    }


}
