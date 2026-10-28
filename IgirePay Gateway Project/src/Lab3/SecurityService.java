package Lab3;

import Lab1.Account;
import Lab2.AccountDAO;
import java.sql.SQLException;

public class SecurityService {
    private AccountDAO accountDAO = new AccountDAO();

    public boolean validatePin(int accountId, String pin) throws SQLException {
        Account acc = accountDAO.read(accountId);
        return acc != null && acc.getPin().equals(pin);
    }

    public void updatePin(int accountId, String oldPin, String newPin) throws SQLException, Exception {
        Account acc = accountDAO.read(accountId);
        if (acc == null) throw new Exception("Account not found.");
        if (!acc.getPin().equals(oldPin)) throw new Exception("Old PIN verification failed.");
        if (newPin.length() != 4 || !newPin.matches("\\d+")) throw new Exception("PIN must be exactly 4 digits.");

        acc.setPin(newPin);
        accountDAO.updateBalanceAndPin(acc);
    }
}
