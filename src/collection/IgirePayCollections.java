package collection;

import model.Account;
import model.Customer;
import model.Transaction;

import java.util.*;

public class IgirePayCollections {

    private List<Customer> customers = new ArrayList<>();

    private List<Transaction> transactionHistory = new ArrayList<>();

    private Set<String> processedReferenceIds = new HashSet<>();

    private Map<Integer, Account> accounts = new HashMap<>();

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void addAccount(Account account) {
        accounts.put(account.getId(), account);
    }

    public boolean processReference(String referenceId) {

        if (processedReferenceIds.contains(referenceId)) {
            return false;
        }

        processedReferenceIds.add(referenceId);
        return true;
    }

    public void addTransaction(Transaction transaction) {
        transactionHistory.add(transaction);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public Set<String> getProcessedReferenceIds() {
        return processedReferenceIds;
    }

    public Map<Integer, Account> getAccounts() {
        return accounts;
    }
}