package bankProducts.test;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import bankProducts.CreditCard;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditCardTest {
    private static final String CREDIT_CARD_CURRENCY = "RUB";
    private static final String CREDIT_CARD_NAME = "Credit Card";
    private static final double CREDIT_CARD_RATE = 12.0;
    private static final Random random = new Random();
    private CreditCard creditCard;

    @RepeatedTest(5)
    void checkCreditCardDebtWithNegativeBalanceTest() {
        creditCard = new CreditCard(CREDIT_CARD_CURRENCY, -random.nextDouble() * 100,
                CREDIT_CARD_NAME, CREDIT_CARD_RATE);
        assertEquals(Math.abs(creditCard.getBalance()) * (1 + creditCard.getInterestRate() / 100),
                creditCard.checkDebt(), "Expected debt is not equal to the actual debt on the card");
    }

    @RepeatedTest(5)
    void checkCreditCardDebtWithPositiveBalanceTest() {
        creditCard = new CreditCard(CREDIT_CARD_CURRENCY, random.nextDouble() * 10,
                CREDIT_CARD_NAME, CREDIT_CARD_RATE);
        assertEquals(0, creditCard.checkDebt(), "Еhe debt should be 0 with a positive balance");
    }

    @ParameterizedTest
    @CsvSource({"300.0, 150.0", "-300.0, 10.19", "18.31, 90.99"})
    void verifyCreditCardTopUpOperationTest(double initialBalance, double topUpSum) {
        creditCard = new CreditCard(CREDIT_CARD_CURRENCY, initialBalance, CREDIT_CARD_NAME, CREDIT_CARD_RATE);
        creditCard.topUp(topUpSum);
        assertEquals(initialBalance + topUpSum, creditCard.getBalance(),
                "The card balance " + initialBalance + " did not increase by " + topUpSum);
    }

    @ParameterizedTest
    @CsvSource({"300.0, 150.0", "-300.0, 10.19", "18.31, 90.99"})
    void verifyCreditCardWithdrawOperationTest(double initialBalance, double withdrawSum) {
        creditCard = new CreditCard(CREDIT_CARD_CURRENCY, initialBalance, CREDIT_CARD_NAME, CREDIT_CARD_RATE);
        creditCard.withdraw(withdrawSum);
        assertEquals(initialBalance - withdrawSum, creditCard.getBalance(),
                "The card balance " + initialBalance + " did not decrease by " + withdrawSum);
    }
}
