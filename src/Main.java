import service.PaymentService;

public class Main {

    public static void main(String[] args) {

        PaymentService paymentService =
                new PaymentService();

        paymentService.deposit(
                1000,
                500,
                "DEP100"
        );

        paymentService.deposit(
                1000,
                500,
                "DEP100"
        );

        paymentService.withdraw(
                2000,
                1000,
                "WIT100"
        );
    }
}