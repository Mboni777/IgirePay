import service.PaymentService;

public class Main {

    public static void main(String[] args) {

        PaymentService paymentService =
                new PaymentService();

        paymentService.processTransaction("REF001");

        paymentService.processTransaction("REF001");
    }
}