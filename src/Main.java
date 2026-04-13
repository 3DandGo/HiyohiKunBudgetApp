import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        BudgetManager manager = new BudgetManager();
        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while(running){

            System.out.println("\n======= Budget Tracker =======");
            System.out.println("1. Add Transaction");
            System.out.println("2. View Transactions");
            System.out.println("3. View Summary");
            System.out.println("4. Search Transactions");
            System.out.println("5. Save");
            System.out.println("6. Load");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                case 1:
                    addTransaction(manager, scanner);
                    break;

                case 2:
                    viewTransaction(manager);
                    break;

                case 3:
                    viewSummary(manager);
                    break;

                case 4:
                    System.out.println("======= Search Transactions =======");
                    System.out.println("1. Search By Name");
                    System.out.println("2. Search By Category");
                    System.out.print("Choose your option: ");
                    Integer choiceinput = scanner.nextInt();
                    scanner.nextLine(); // CLEAR BUFFER

                    switch(choiceinput)
                    {
                        case 1:
                            searchForTransaction(manager, scanner, choiceinput);
                            break;

                        case 2:
                            searchForTransaction(manager, scanner, choiceinput);
                            break;

                        default:
                            System.out.println("Invalid Input");
                            break;
                    }
                    break;

                case 5:
                    FileManager.saveToFile("budget.csv", manager.getAllTransactions());
                    break;

                case 6:
                    manager.getAllTransactions().clear();
                    manager.getAllTransactions().addAll(FileManager.loadFromFile("budget.csv"));
                    break;

                case 7:
                    System.out.println("Exiting...");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
        scanner.close();
    }

    private static void addTransaction(BudgetManager manager, Scanner scanner){
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter category: ");
        String category = scanner.nextLine();

        System.out.print("Is this an expense? (True/False): ");
        boolean isExpense = scanner.nextBoolean();
        scanner.nextLine();

        Transaction t = new Transaction(name, description, amount, category, LocalDate.now(), isExpense);

        manager.addTransaction(t);
        System.out.println("Transaction " + t.getTransactionName() + " added");
    }

    private static void viewTransaction(BudgetManager manager){
        if(manager.getAllTransactions().isEmpty()){
            System.out.println("No transactions found");
            return;
        }

        for (Transaction t : manager.getAllTransactions()){
            System.out.println(t);
        }
    }

    private static void viewSummary(BudgetManager manager){
        System.out.println("Total Income: " + manager.getTotalIncome() + "円");
        System.out.println("Total Expenses: " + manager.getTotalExpenses() + "円");
        System.out.println("Balance: " + manager.getBalance() + "円");
    }

    private static void searchForTransaction(BudgetManager manager, Scanner scanner, Integer choiceinput){
        Integer choice = choiceinput;
        String entry = "none";

        switch(choice)
        {
            case 1:
                System.out.print("Enter Transaction Name: ");
                entry = scanner.nextLine();

                List<Transaction> results = manager.searchByName(entry);

                if (results.isEmpty()){
                    System.out.println("No Transactions Found.");
                    return;
                }

                System.out.println("\n--- Search Results ---");
                for (Transaction t : results)
                {
                    System.out.println(t);
                }
                break;

            case 2:
                System.out.print("Enter Transaction Category: ");
                entry = scanner.nextLine();

                results = manager.searchByCategory(entry);

                if (results.isEmpty()){
                    System.out.println("No Transactions Found");
                    return;
                }

                System.out.println("\n--- Search Results ---");
                for (Transaction t : results)
                {
                    System.out.println(t);
                }
                break;

            default:
                System.out.println("Invalid choice");
                break;
        }
    }
}
