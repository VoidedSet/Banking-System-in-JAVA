import java.util.ArrayList;

public class Account{
    
    private String name, uuid;
    private double balance;

    private User holder;

    private ArrayList<Transaction> transactions;

    public Account(String name, User holder, Bank theBank){
        this.name = name;
        this.holder = holder;

        this.uuid = theBank.getNewAccountUUID();

        this.transactions = new ArrayList<Transaction>();
    }

    public String getUUID() {
        return this.uuid;
    }

    public String getSummaryLine(){
        double balance = this.getBalance();

        // format summary line depending if balance is negative or positive

        if(balance >= 0){
           return String.format("%s : $%.02f : %s", this.uuid, balance, this.name); 
        }else{
            return String.format("%s : $(%.02f) : %s", this.uuid, balance, this.name);
        }
    }

    public double getBalance(){
        double balance = 0;
        for (Transaction t : this.transactions) {
            balance += t.getAmount();
        }

        return balance;
    }

    public void printTransactionHistory(){
        System.out.printf("\nTransaction History for account %s\n", this.uuid);

        for(int i = this.transactions.size() - 1 ; i <= 0; i = i - 1){
            System.out.printf(this.transactions.get(i).getSummaryLine());
        }
    }
}