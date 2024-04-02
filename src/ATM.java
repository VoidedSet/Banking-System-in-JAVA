import java.util.Scanner;

public class ATM {

    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args){
       // Scanner sc = new Scanner(System.in);

        Bank bank = new Bank("K Bank");

        User user1 = bank.addUser("Kshayik", "Doshi", "420");

        Account newAccount = new Account("Checking", user1, bank);
        user1.addAccount(newAccount);
        bank.addAccount(newAccount);


        //LOGIN UI AHAHAH
            User currentUser;

        while (true) { 
            //will run as long as login is failing.

            currentUser = ATM.menuPrompt(bank);

            //Stay here till the user quits.

            ATM.printUserMenu(currentUser);
        }
    }

    public static User menuPrompt(Bank bank){
        String userId;
        String pin;
        User authUser;

        do {
            System.out.printf("\n\t\tWelcome to %s!!", bank.getName());
            System.out.println("\nEnter user ID: ");
            userId = sc.nextLine();

            System.out.println("Enter Pin ");
            pin = sc.nextLine();

            authUser = bank.userLogin(userId, pin); 

            if(authUser == null){
                System.out.println("\nlogin failed");
            }

        } while (authUser == null);

        return authUser;
    }
    
    public static void printUserMenu(User theUser){

        //print user account summary
        theUser.printAccountsSummary();

        int choice;

        do{
            System.out.printf("Welcome %s, what would you like to do?", theUser.getFirstName());
            System.out.println("1) Transaction history\n2) Withdraw\n3) Deposit\n4) Transfer 5) Quit");

            System.out.println("\nEnter your choice:\n");
            choice = sc.nextInt();

            if(choice < 1 || choice > 5){
                System.out.println("\nPlease enter valid choice.");
            }
        }while(choice < 1 || choice > 5);

        switch (choice) {
            case 1:
                ATM.showTransactionHistory(theUser);
                break;
            
            case 2:
                ATM.fundWithdrawal(theUser);
                break;

            case 3:
                ATM.depositFunds(theUser);
                break;

            case 4:
                ATM.fundTransfer(theUser);
                break;
        }

        if(choice != 5){
            ATM.printUserMenu(theUser);
        }
    }

    public static void showTransactionHistory(User theUser){

        int theAcct;
        do{
            System.out.printf("Enter the number of account (1 - %d) of the account who's transaction you want to see: ", theUser.numAccounts());
            theAcct = sc.nextInt() - 1;

            if(theAcct < 0 || theAcct >= theUser.numAccounts()){
                System.out.println("Invalid account");
            }

        }while(theAcct < 0 || theAcct >= theUser.numAccounts());

        //print transaction history

        theUser.printAcctTransactionHistory(theAcct);
    }

    public static void fundWithdrawal(User thUser){

    }

    public static void depositFunds(User thUser){

    }

    public static void fundTransfer(User theUser){
        int fromAcct, toAcct;
        double amount, acctBal;

        do{
            System.out.printf("Enter the number (1 - %d) of account to transfer from \n", theUser.numAccounts());
            fromAcct = sc.nextInt() - 1;

            if(fromAcct < 0 || fromAcct >= theUser.numAccounts()){
                System.out.println("Invalid Account please try again.");
            }

        }while(fromAcct < 0 || fromAcct >= theUser.numAccounts());

        acctBal = theUser.getAcctBalance(fromAcct);
    }
}
