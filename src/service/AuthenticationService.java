package service;

public class AuthenticationService {

    public boolean validatePin(String savedPin,
                                String enteredPin) {

        return savedPin.equals(enteredPin);
    }

    public String changePin(String oldPin,
                            String currentPin,
                            String newPin) {

        if (oldPin.equals(currentPin)) {

            System.out.println("PIN changed successfully.");
            return newPin;
        }

        System.out.println("Invalid current PIN.");
        return oldPin;
    }
}