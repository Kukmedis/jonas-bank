package lt.keturka.jonasbank.core.domain;

import javax.money.MonetaryAmount;
import java.time.Instant;
import java.util.Objects;

public class MoneyTransfer {

    public final String id;

    public final String debitAccountId;

    public final String creditAccountId;

    public final MonetaryAmount amount;

    public final Instant transferDate;

    public MoneyTransfer(
            String id, String debitAccountId, String creditAccountId, MonetaryAmount amount, Instant transferDate) {
        this.id = id;
        this.debitAccountId = debitAccountId;
        this.creditAccountId = creditAccountId;
        this.amount = amount;
        this.transferDate = transferDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoneyTransfer that = (MoneyTransfer) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(debitAccountId, that.debitAccountId) &&
                Objects.equals(creditAccountId, that.creditAccountId) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(transferDate, that.transferDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, debitAccountId, creditAccountId, amount, transferDate);
    }
}
