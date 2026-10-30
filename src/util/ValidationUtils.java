package util;

public class ValidationUtils {

    public static boolean isValidAmount(double amount) {
        return amount > 0;
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.contains("@");
    }
}