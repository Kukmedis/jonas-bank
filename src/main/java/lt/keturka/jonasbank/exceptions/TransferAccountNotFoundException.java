package lt.keturka.jonasbank.exceptions;

public class TransferAccountNotFoundException extends RuntimeException {

    public final String accountId;

    public TransferAccountNotFoundException(String accountId) {
        this.accountId = accountId;
    }
}
