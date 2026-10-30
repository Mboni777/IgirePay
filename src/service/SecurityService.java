package service;

public class SecurityService {

    public boolean isPinValid(String pin) {

        return pin != null &&
               pin.matches("\\d{4}");
    }
}