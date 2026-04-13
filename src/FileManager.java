import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    public static void saveToFile(String filename, List<Transaction> transactions){
        try(FileWriter writer = new FileWriter(filename)){
            for(Transaction t : transactions) {
                String line = t.getTransactionName() + "," + t.getTransactionDescription() + "," + t.getTransactionAmount() + "," + t.getTransactionCategory() + "," +
                        t.getTransactionLocalDate() + "," + t.isExpense();

                writer.write(line + "\n");
            }

            System.out.println("Data saved successfully");
        }

        catch (IOException e){
            System.out.println("Error saving file.");
            e.printStackTrace();
        }
    }

    public static List<Transaction> loadFromFile(String filename){
        List<Transaction> transactions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line;

            while ((line = reader.readLine()) != null){
                String[] parts = line.split(",");

                String name = parts[0];
                String description = parts[1];
                double amount = Double.parseDouble(parts[2]);
                String category = parts[3];
                LocalDate date = LocalDate.parse(parts[4]);
                boolean isExpense = Boolean.parseBoolean(parts[5]);

                Transaction t = new Transaction(name, description, amount, category, date, isExpense);

                transactions.add(t);
            }

            System.out.println("Data loaded successfully.");
        }

        catch (IOException e){
            System.out.println("Error loading file.");
            e.printStackTrace();
        }
        return transactions;
    }
}
