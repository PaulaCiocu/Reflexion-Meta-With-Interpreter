import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        Store store =new Store();
        DSLInterpreter interpreter = new DSLInterpreter(store);
        String fileName = "src/shop.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String command;
            while ((command = reader.readLine()) != null) {
                interpreter.interpretCommand(command);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n\n");
        store.listAllProducts();
    }

}