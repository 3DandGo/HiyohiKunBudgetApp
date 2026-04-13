import java.time.LocalDate;

public class Transaction {
    private String name;
    private String description;
    private double amount;
    private String category;
    private LocalDate date;
    private boolean isExpense;

    public Transaction(String name, String description, double amount, String category, LocalDate date, boolean isExpense){
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.isExpense = isExpense;
    }

    public String getTransactionName(){
        return name;
    }

   public String getTransactionDescription(){
        return description;
    }
    public double getTransactionAmount(){
        return amount;
    }

    public String getTransactionCategory(){
        return category;
    }

    public LocalDate getTransactionLocalTime(){
        return date;
    }

    public boolean isExpense(){
        return isExpense;
    }

    @Override
    public String toString(){
        return date + " | " + category + " | " + name + " | " + description + " | " + amount + " 円 | " + (isExpense ? "(Expense)" : "(Income)");
    }
}
