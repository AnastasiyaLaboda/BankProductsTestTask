package bankProducts.test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import bankProducts.BankDeposit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankDepositTest {
    private static final String DEPOSIT_CURRENCY = "RUB";
    private static final String DEPOSIT_NAME = "Bank Deposit";
    private BankDeposit deposit;

    @ParameterizedTest
    @CsvSource({"3000.0, 15000000.0", "15000000.0, 300.70", "10087951.19, 90177.99"})
    void verifyDepositTopUpOperationTest(double initialBalance, double topUpSum) {
        deposit = new BankDeposit(DEPOSIT_CURRENCY, initialBalance, DEPOSIT_NAME);
        deposit.topUp(topUpSum);
        assertEquals(initialBalance + topUpSum, deposit.getBalance(),
                "The deposit balance " + initialBalance + " did not increase by " + topUpSum);
    }

    @ParameterizedTest
    @ValueSource(doubles = {3000058.0, 1879520.0, 10.27, 1808.3, 0.99})
    void verifyCloseDepositTest(double initialBalance) {
        deposit = new BankDeposit(DEPOSIT_CURRENCY, initialBalance, DEPOSIT_NAME);
        deposit.close();
        assertEquals(0.0, deposit.getBalance(), "After closing the deposit, it's balance was not reset");
    }
}
