package lt.keturka.jonasbank.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.money.MonetaryAmount;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransferRequestApiModel {

    @NotNull
    private MonetaryAmount amount;

    @NotNull
    private String debitAccountId;

    @NotNull
    private String creditAccountId;

    public MonetaryAmount getAmount() {
        return amount;
    }

    public void setAmount(MonetaryAmount amount) {
        this.amount = amount;
    }

    public String getDebitAccountId() {
        return debitAccountId;
    }

    public void setDebitAccountId(String debitAccountId) {
        this.debitAccountId = debitAccountId;
    }

    public String getCreditAccountId() {
        return creditAccountId;
    }

    public void setCreditAccountId(String creditAccountId) {
        this.creditAccountId = creditAccountId;
    }
}
