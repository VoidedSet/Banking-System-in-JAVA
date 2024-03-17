import java.util.ArrayList;
import java.util.Random;

public class Bank {
    private String name;
    private ArrayList<User> users;
    private ArrayList<Account> accounts;

    public Bank(String name){
        this.name = name;
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();
    }

    public String getNewUserUUID(){

        String uuid;
        Random rand = new Random();
        int len = 5;
        boolean notUnique;

        do {
            uuid = "";

            for(int i = 0; i < len; i++){
                uuid += ((Integer)rand.nextInt(10)).toString();
            }

            notUnique = false;

            for (User u : this.users) {
                if(uuid.compareTo(u.getUUID()) == 0){
                    notUnique = true;
                    break;
                }
            }
        } while (notUnique);

        return uuid;

    }

    public String getNewAccountUUID(){

        String uuid;
        Random rand = new Random();
        int len = 10;
        boolean notUnique;

        do {
            uuid = "";

            for(int i = 0; i < len; i++){
                uuid += ((Integer)rand.nextInt(10)).toString();
            }

            notUnique = false;

            for (Account a : this.accounts) {
                if(uuid.compareTo(a.getUUID()) == 0){
                    notUnique = true;
                    break;
                }
            }
        } while (notUnique);

        return uuid;

    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public User addUser(String fName, String lastN, String pin){
        User newUser = new User(lastN, lastN, pin, this);
        this.users.add(newUser);

        Account newAccount = new Account("Savings", newUser, this);
        newUser.addAccount(newAccount);
        this.addAccount(newAccount);

        return newUser;
    }

    public User userLogin(String userId, String pin){

        for (User u : this.users) {
            if(u.getUUID().compareTo(userId) == 0 && u.validatePin(pin)){
                return u;
            }
        }

        return null;
    }

    public String getName(){
        return this.name;
    }
}
