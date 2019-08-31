package lt.keturka.jonasbank.api;

import javax.money.MonetaryAmount;
import java.time.Instant;

public class TransferResponseApiModel {

    public final String id;

    public final MonetaryAmount amount;

    public final String debitAccountId;

    public final String creditAccountId;

    public final Instant transferDate;

    public TransferResponseApiModel(
            String id, MonetaryAmount amount, String debitAccountId, String creditAccountId, Instant transferDate) {
        this.id = id;
        this.amount = amount;
        this.debitAccountId = debitAccountId;
        this.creditAccountId = creditAccountId;
        this.transferDate = transferDate;
    }
}
