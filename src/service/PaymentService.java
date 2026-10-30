package service;

import dao.ProcessedRequestDAO;

public class PaymentService {

    private final ProcessedRequestDAO processedRequestDAO =
            new ProcessedRequestDAO();

    public void processTransaction(String referenceId) {

        if (processedRequestDAO.referenceExists(referenceId)) {

            System.out.println(
                    "Duplicate transaction detected. Rejected."
            );

            return;
        }

        System.out.println(
                "Transaction processed successfully."
        );

        processedRequestDAO.saveReference(referenceId);
    }
}