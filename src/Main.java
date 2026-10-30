import dao.CustomerDAO;
import model.Customer;

public class Main {

    public static void main(String[] args) {

        Customer customer =
                new Customer(
                        0,
                        "Gervais Mboninkunda",
                        "gervais@example.com",
                        "+233257360503"
                );

        CustomerDAO customerDAO = new CustomerDAO();

        customerDAO.addCustomer(customer);
    }
}