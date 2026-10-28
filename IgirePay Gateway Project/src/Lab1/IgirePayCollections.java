package Lab1;

import java.util.*;

public class IgirePayCollections {
    private List<Transaction> failedLogs = new ArrayList<>();
    private Map<Integer, Customer> customerMap = new HashMap<>();

    public void logFailedTransaction(Transaction tx) { failedLogs.add(tx); }
    public List<Transaction> getFailedLogs() { return failedLogs; }
    public void cacheCustomer(Customer customer) { customerMap.put(customer.getId(), customer); }
    public Customer getCachedCustomer(int id) { return customerMap.get(id); }
}
