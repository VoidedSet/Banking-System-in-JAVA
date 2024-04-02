import java.util.Date;

public class Transaction{
    private double amount;
    private Date timeStamp;

    private String statememt;
    private Account inAccount;

    public Transaction(double amount, Account inAccount){

        this. amount = amount;
        this.inAccount = inAccount;
        this.timeStamp = new Date();
        this.statememt = "";

    }

    public Transaction(double amount, Account inAccount, String statement){

            //calling the 2 parameter Transaction function and then updating memo.
        this(amount, inAccount);
        this.statememt = statement;

    }

    public double getAmount(){
        return this.amount;
    }

    public String getSummaryLine(){
        if(this.amount >= 0){
            return String.format("%s : $%.02f : %s", this.timeStamp.toString(), this.amount, this.statememt);
        }else{
            return String.format("%s : $(%.02f) : %s", this.timeStamp.toString(), this.amount, this.statememt);
        }
    }

}