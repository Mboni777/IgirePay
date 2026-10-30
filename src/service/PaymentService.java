package service;

import java.util.HashSet;
import java.util.Set;

public class PaymentService {

    private final Set<String> processedReferences =
            new HashSet<>();

    public boolean processReference(String referenceId) {

        if (processedReferences.contains(referenceId)) {

            System.out.println(
                    "Duplicate transaction detected. Rejected."
            );

            return false;
        }

        processedReferences.add(referenceId);

        System.out.println(
                "Reference stored successfully."
        );

        return true;
    }
}