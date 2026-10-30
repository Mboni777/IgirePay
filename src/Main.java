import dao.AccountDAO;
import model.WalletAccount;

public class Main {

    public static void main(String[] args) {

        AccountDAO dao = new AccountDAO();

        WalletAccount account =
                new WalletAccount(
                        0,
                        1,
                        1000.0,
                        "1234"
                );

        dao.createAccount(account);
    }
}