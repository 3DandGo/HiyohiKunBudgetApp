import java.util.ArrayList;
import java.util.List;

public class BudgetManager {
    private List<Transaction> transactions;

    public BudgetManager(){
        transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }

    public List<Transaction> getAllTransactions(){
        return transactions;
    }

    public double getTotalExpenses(){
        double total = 0;

        for(Transaction t : transactions)
        {
            if(t.isExpense()){
                total += t.getTransactionAmount();
            }
        }
        return total;
    }

    public double getTotalIncome(){
        double total = 0;

        for(Transaction t : transactions)
        {
            if(!t.isExpense()){
                total += t.getTransactionAmount();
            }
        }
        return total;
    }

   public List<Transaction> getTransactionByCategory(String category){
        List<Transaction> total = new ArrayList<>();

        for (Transaction t : transactions)
        {
            if(t.getTransactionCategory().equals(category))
            {
                total.add(t);
            }
        }
        return total;
   }

   /*
   public List<Transaction> searchByName(String keyword){
        List<Transaction> results = new ArrayList<>();

        for (Transaction t : transactions){
            if(t.getName().toLowerCase().contains(keyword.toLowerCase())){
                results.add(t);
            }
        }
        return results;
    }

    public List<Transaction> searchByCategory(String keyword){
        List<Transaction> results = new ArrayList<>();

        for(Transaction t : transactions)
        {
            if(t.getCategory().toLowerCase().contains(keyword.toLowerCase())){
                results.add(t);
            }
        }
        return results;
    }
    */
}
