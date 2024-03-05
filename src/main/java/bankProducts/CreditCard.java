package bankProducts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreditCard implements Card {
    private String currency;
    private double balance;
    private String name;
    private double interestRate;

    public double checkDebt() {
        if (balance < 0) {
            return Math.abs(balance) * (1 + interestRate / 100);
        } else {
            return 0;
        }
    }

    @Override
    public void topUp(double amount) {
        setBalance(getBalance() + amount);
    }

    @Override
    public void withdraw(double amount) {
        setBalance(getBalance() - amount);
    }
}
