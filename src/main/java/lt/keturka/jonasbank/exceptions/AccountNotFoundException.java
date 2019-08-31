package lt.keturka.jonasbank.exceptions;

public class AccountNotFoundException extends RuntimeException {

    public final String accountId;

    public AccountNotFoundException(String accountId) {
        this.accountId = accountId;
    }
}
