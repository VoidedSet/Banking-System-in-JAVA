import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User{
    private String firstN, lastN, uuid;
    private byte pinHash[];

    private ArrayList<Account> accounts;

     public User(String firstN, String lastN, String pin, Bank theBank){
        this.firstN = firstN;
        this.lastN = lastN;

        //pin is hashed using MD5 algo
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());

        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error no such algorithm");
            e.printStackTrace();
            System.exit(1);
        }

        //new uuid gen
        this.uuid = theBank.getNewUserUUID();

        this.accounts = new ArrayList<Account>();
        System.out.printf("New User with name - %s %s and ID %s created", firstN, lastN, uuid);


    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public String getUUID() {
        return this.uuid;
    }

    public boolean validatePin(String apin){
        
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(apin.getBytes()), this.pinHash);
        }catch (NoSuchAlgorithmException e) {
            System.err.println("Error no such algorithm");
            e.printStackTrace();
            System.exit(1);
        }

        return false;

    }
}