package Lab2;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.UUID;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Welcome to IgirePay Payment Gateway");

        while (true) {
            System.out.println("\n===== LAB 2 MENU =====");
            System.out.println("1. Add customer");
            System.out.println("2. View customers");
            System.out.println("3. Update customer");
            System.out.println("4. Create account");
            System.out.println("5. Deposit money");
            System.out.println("6. Withdraw money");
            System.out.println("7. Transfer money");
            System.out.println("8. View transaction history");
            System.out.println("9. Delete inactive account");
            System.out.println("0. Exit");

            int choice = readInt("Choose option: ");

            if (choice == 0) {
                System.out.println("Goodbye!");
                break;
            }

            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("Full name: ");
                        String name = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Phone: ");
                        String phone = scanner.nextLine();

                        // Connect this to CustomerDAO
                        System.out.println("Customer details received.");
                    }

                    case 2 ->
                            System.out.println("Connect this option to CustomerDAO.");

                    case 3 ->
                            System.out.println("Connect this option to CustomerDAO.");

                    case 4 ->
                            System.out.println("Connect this option to AccountDAO.");

                    case 5, 6, 7 -> {
                        System.out.print("Reference ID (Enter to generate): ");
                        String reference = scanner.nextLine().trim();

                        if (reference.isEmpty()) {
                            reference = "REF-" + UUID.randomUUID();
                        }

                        System.out.println("Reference ID: " + reference);
                        System.out.println(
                                "Connect this option to TransactionDAO."
                        );
                    }

                    case 8 ->
                            System.out.println("Connect this option to TransactionDAO.");

                    case 9 ->
                            System.out.println("Connect this option to AccountDAO.");

                    default ->
                            System.out.println("Invalid option.");
                }

            } catch (RuntimeException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static int readInt(String message) {
        System.out.print(message);

        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            return -1;
        }
    }
}
