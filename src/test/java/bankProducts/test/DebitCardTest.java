package bankProducts.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import bankProducts.DebitCard;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DebitCardTest {
    private static final String DEBIT_CARD_CURRENCY = "RUB";
    private static final double DEBIT_CARD_INITIAL_BALANCE = 1000.50;
    private static final String DEBIT_CARD_NAME = "Debit Card";
    private DebitCard debitCard;

    @BeforeEach
    public void createDebitCard() {
        debitCard = new DebitCard(DEBIT_CARD_CURRENCY, DEBIT_CARD_INITIAL_BALANCE, DEBIT_CARD_NAME);
    }

    @ParameterizedTest
    @ValueSource(doubles = {300.0, 150.0, 300.0, 10.19, 18.31, 90.99})
    void verifyDebitCardTopUpOperationTest(double topUpSum) {
        debitCard.topUp(topUpSum);
        assertEquals(DEBIT_CARD_INITIAL_BALANCE + topUpSum, debitCard.getBalance(),
                "The card balance " + DEBIT_CARD_INITIAL_BALANCE + " did not increase by " + topUpSum);
    }

    @ParameterizedTest
    @ValueSource(doubles = {3000058.0, 1879520.0, 10.27, 1808.3, 0.99})
    void verifyDebitCardWithdrawOperationTest(double withdrawSum) {
        debitCard.withdraw(withdrawSum);
        assertEquals(DEBIT_CARD_INITIAL_BALANCE - withdrawSum, debitCard.getBalance(),
                "The card balance " + DEBIT_CARD_INITIAL_BALANCE + " did not decrease by " + withdrawSum);
    }
}
