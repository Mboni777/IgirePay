package Lab3;

import Lab1.Customer;
import Lab1.WalletAccount;
import Lab1.SavingsAccount;
import Lab1.Account;
import Lab1.Transaction;
import Lab2.*;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerDAO customerDAO = new CustomerDAO();
        AccountDAO accountDAO = new AccountDAO();
        TransactionDAO transactionDAO = new TransactionDAO();
        PaymentService paymentService = new PaymentService();
        SecurityService securityService = new SecurityService();
        ReportService reportService = new ReportService();

        System.out.println("=== Welcome to IgirePay Payment Gateway System ===");

        while (true) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Register Customer");
            System.out.println("2. Open Digital Account");
            System.out.println("3. Deposit Money");
            System.out.println("4. Withdraw Money");
            System.out.println("5. Peer-to-Peer Transfer");
            System.out.println("6. Account Balance Lookup");
            System.out.println("7. View Statement History");
            System.out.println("8. Export Account Report to CSV");
            System.out.println("9. Change Security PIN");
            System.out.println("10. Exit Application");
            System.out.print("Select operational request variant: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Eat trailing break

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter full name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter structural email lookup address: ");
                        String email = scanner.nextLine();
                        System.out.print("Enter validation phone number: ");
                        String phone = scanner.nextLine();
                        Customer customer = new Customer(0, name, email, phone);
                        customerDAO.create(customer);
                        System.out.println("Customer saved successfully! Generated System Key ID: " + customer.getId());
                        break;

                    case 2:
                        System.out.print("Enter target Customer ID: ");
                        int custId = scanner.nextInt();
                        System.out.print("Account Type (1 for WALLET, 2 for SAVINGS): ");
                        int typeChoice = scanner.nextInt();
                        System.out.print("Set an initialization 4-digit PIN string: ");
                        String pin = scanner.next();

                        Account newAccount = (typeChoice == 1)
                                ? new WalletAccount(0, custId, 80000, pin)
                                : new SavingsAccount(0, custId, 1000, pin);

                        accountDAO.create(newAccount);
                        System.out.println("Digital ledger interface binding processed successfully.");
                        break;

                    case 3:
                        System.out.print("Enter Destination Account ID: ");
                        int depAccId = scanner.nextInt();
                        System.out.print("Enter Deposit Ledger Value: ");
                        double depAmt = scanner.nextDouble();
                        String depRef = UUID.randomUUID().toString(); // Auto generation ensures tracking security
                        System.out.println("Auto Generated Transaction Reference: " + depRef);

                        paymentService.processDeposit(depAccId, depAmt, depRef);
                        System.out.println("Deposit executed seamlessly.");
                        break;

                    case 4:
                        System.out.print("Enter Source Account ID: ");
                        int witAccId = scanner.nextInt();
                        System.out.print("Enter Withdrawal Value: ");
                        double witAmt = scanner.nextDouble();
                        System.out.print("Enter verification account PIN: ");
                        String witPin = scanner.next();
                        String witRef = UUID.randomUUID().toString();

                        paymentService.processWithdraw(witAccId, witAmt, witPin, witRef);
                        System.out.println("Withdrawal update processed successfully.");
                        break;

                    case 5:
                        System.out.print("Enter Origin Account ID: ");
                        int srcId = scanner.nextInt();
                        System.out.print("Enter Beneficiary Account ID: ");
                        int destId = scanner.nextInt();
                        System.out.print("Enter Transfer Amount: ");
                        double transAmt = scanner.nextDouble();
                        System.out.print("Enter security authorization PIN: ");
                        String transPin = scanner.next();
                        String transRef = UUID.randomUUID().toString();

                        paymentService.processTransfer(srcId, destId, transAmt, transPin, transRef);
                        System.out.println("Fund settlement transfer completed.");
                        break;

                    case 6:
                        System.out.print("Enter Lookup Account ID: ");
                        int balId = scanner.nextInt();
                        Account accObj = accountDAO.read(balId);
                        if (accObj != null) {
                            System.out.println("Current Available Balances: RWF " + accObj.getBalance());
                        } else {
                            System.out.println("Account record not active or not found.");
                        }
                        break;

                    case 7:
                        System.out.print("Enter Target Audit Account ID: ");
                        int histId = scanner.nextInt();
                        List<Transaction> history = transactionDAO.getHistoryByAccount(histId);
                        System.out.println("--- Statement Run ---");
                        for (Transaction t : history) {
                            System.out.println(t);
                        }
                        break;

                    case 8:
                        System.out.print("Enter Target Account ID for export: ");
                        int expId = scanner.nextInt();
                        String fileDest = "statement_account_" + expId + ".csv";
                        reportService.exportToCSV(expId, fileDest);
                        System.out.println("Data extraction complete. Report saved to: " + fileDest);
                        break;

                    case 9:
                        System.out.print("Enter Target Account ID: ");
                        int securityId = scanner.nextInt();
                        System.out.print("Enter Old Account PIN: ");
                        String oldP = scanner.next();
                        System.out.print("Enter New 4-digit Account PIN: ");
                        String newP = scanner.next();
                        securityService.updatePin(securityId, oldP, newP);
                        System.out.println("Security parameters changed successfully.");
                        break;

                    case 10:
                        System.out.println("Terminating session safely. Goodbye!");
                        scanner.close();
                        System.exit(0);

                    default:
                        System.out.println("Invalid operational configuration strategy.");
                }
            } catch (Exception e) {
                System.out.println(" System Exception Triggered: " + e.getMessage());
            }
        }
    }
}
