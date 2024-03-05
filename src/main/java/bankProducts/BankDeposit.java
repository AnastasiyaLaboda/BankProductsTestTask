package bankProducts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BankDeposit implements Deposit {
    private String currency;
    private double balance;
    private String name;

    @Override
    public void topUp(double amount) {
        setBalance(getBalance() + amount);
    }

    @Override
    public void close() {
        this.balance = 0;
    }
}
