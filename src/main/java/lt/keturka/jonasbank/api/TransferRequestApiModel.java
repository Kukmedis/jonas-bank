package lt.keturka.jonasbank.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.money.MonetaryAmount;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransferRequestApiModel {

    public final MonetaryAmount amount;

    public final String debitAccountId;

    public final String creditAccountId;

    public TransferRequestApiModel(
            @NotNull MonetaryAmount amount,
            @NotNull String debitAccountId,
            @NotNull String creditAccountId) {
        this.amount = amount;
        this.debitAccountId = debitAccountId;
        this.creditAccountId = creditAccountId;
    }
}
