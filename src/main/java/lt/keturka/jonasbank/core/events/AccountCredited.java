package lt.keturka.jonasbank.core.events;

public class AccountCredited {

    public final String accountId;

    public final String monetaryAmount;

    public AccountCredited(String accountId, String monetaryAmount) {
        this.accountId = accountId;
        this.monetaryAmount = monetaryAmount;
    }
}
