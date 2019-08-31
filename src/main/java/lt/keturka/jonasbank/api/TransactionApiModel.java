package lt.keturka.jonasbank.api;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.money.MonetaryAmount;
import java.time.Instant;

public class TransactionApiModel {

    public final String id;

    public final MonetaryAmount amount;

    public final String debitAccountId;

    public final String creditAccountId;

    public final Instant transactionDate;

    public TransactionApiModel(
            String id, MonetaryAmount amount, String debitAccountId, String creditAccountId, Instant transactionDate) {
        this.id = id;
        this.amount = amount;
        this.debitAccountId = debitAccountId;
        this.creditAccountId = creditAccountId;
        this.transactionDate = transactionDate;
    }
}
