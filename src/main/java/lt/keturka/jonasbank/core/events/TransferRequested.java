package lt.keturka.jonasbank.core.events;

import javax.money.MonetaryAmount;

public class TransferRequested {

    public final String transferId;

    public final MonetaryAmount monetaryAmount;

    public final String debitAccountId;

    public final String creditAccountId;

    public TransferRequested(
            String transferId, MonetaryAmount monetaryAmount, String debitAccountId, String creditAccountId) {
        this.transferId = transferId;
        this.monetaryAmount = monetaryAmount;
        this.debitAccountId = debitAccountId;
        this.creditAccountId = creditAccountId;
    }
}
