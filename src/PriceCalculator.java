public class PriceCalculator {
    public double computePrice(Product product, String priceFormula){
        String[] words=priceFormula.split("\\s+");
        StringBuilder result=new StringBuilder();

        for(String word:words){
            if(product.hasProperty(word)){
                Object propertyValue= product.getPropertyValue(word);
                result.append(propertyValue).append(" ");
            } else if (word.contains("(") && word.contains(")")) {
                String functionName = word.substring(0, word.indexOf("("));
                String argument = word.substring(word.indexOf("(") + 1, word.indexOf(")"));
                double functionResult = evaluateFunction(functionName, argument);
                result.append(functionResult).append(" ");
            }
            else{
                result.append(word).append(" ");
            }
        }
        return evaluateExpression(result.toString());


    }
    private double evaluateFunction(String functionName, String argument) {

        switch (functionName) {
            case "Lookup":
                return 10 * argument.length();
            default:
                throw new IllegalArgumentException("Unknown function: " + functionName);
        }
    }

    private double evaluateExpression(String expression) {
        System.out.println(expression);
        String[] parts = expression.trim().split("\\s+");
        double result = Double.parseDouble(parts[0]);
        for (int i = 1; i < parts.length; i += 2) {
            String operator = parts[i];
            double operand = Double.parseDouble(parts[i + 1]);
            switch (operator) {
                case "+":
                    result += operand;
                    break;
                case "-":
                    result -= operand;
                    break;
                case "*":
                    result *= operand;
                    break;
                case "/":
                    result /= operand;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid operator: " + operator);
            }
        }
        return result;
    }

}
