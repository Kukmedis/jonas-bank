package lt.keturka.jonasbank.api;

public class TransferRejectionApiModel {

    public final String reasonCode;

    public final String reason;

    public TransferRejectionApiModel(String reasonCode, String reason) {
        this.reasonCode = reasonCode;
        this.reason = reason;
    }
}
