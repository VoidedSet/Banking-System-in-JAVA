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

    }
}
