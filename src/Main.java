import model.WalletAccount;

public class Main {

    public static void main(String[] args) {

        WalletAccount wallet =
                new WalletAccount(1, 101, 5000, "1234");

        System.out.println(wallet);

        wallet.deposit(1000);

        wallet.withdraw(500);

        System.out.println("Current balance: "
                + wallet.getBalance());
    }
}