package bankProducts;

public interface BankProduct {
    String getCurrency();

    double getBalance();

    String getName();

    default double checkBalance() {
        return getBalance();
    }

    void topUp(double amount);
}
