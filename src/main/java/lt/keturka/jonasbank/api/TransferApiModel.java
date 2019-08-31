package lt.keturka.jonasbank.api;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.money.MonetaryAmount;
import javax.validation.constraints.NotNull;

public class TransferApiModel {

    public final MonetaryAmount amount;

    public final String debitAccountId;

    public final String creditAccountId;

    public TransferApiModel(
            @NotNull MonetaryAmount amount,
            @NotNull String debitAccountId,
            @NotNull String creditAccountId) {
        this.amount = amount;
        this.debitAccountId = debitAccountId;
        this.creditAccountId = creditAccountId;
    }
}
