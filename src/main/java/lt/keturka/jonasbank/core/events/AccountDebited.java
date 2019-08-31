package lt.keturka.jonasbank.core.events;

public class AccountDebited {

    public final String accountId;

    public final String monetaryAmount;

    public AccountDebited(String accountId, String monetaryAmount) {
        this.accountId = accountId;
        this.monetaryAmount = monetaryAmount;
    }
}
