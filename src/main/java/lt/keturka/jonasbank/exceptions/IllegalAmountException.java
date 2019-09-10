package lt.keturka.jonasbank.exceptions;

import javax.money.MonetaryAmount;

/**
 * Exception indicates that transferred amount is not supported (e.g. negative, zero)
 */
public class IllegalAmountException extends RuntimeException {

    public final MonetaryAmount amount;

    public IllegalAmountException(MonetaryAmount amount) {
        this.amount = amount;
    }
}
