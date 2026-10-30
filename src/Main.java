import service.AuthenticationService;
import service.PaymentService;
import service.SecurityService;

public class Main {

    public static void main(String[] args) {

        PaymentService paymentService =
                new PaymentService();

        paymentService.processReference("REF001");

        paymentService.processReference("REF001");

        SecurityService securityService =
                new SecurityService();

        System.out.println(
                securityService.isPinValid("1234")
        );

        AuthenticationService auth =
                new AuthenticationService();

        System.out.println(
                auth.validatePin("1234", "1234")
        );
    }
}