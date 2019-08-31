package lt.keturka.jonasbank.api;

public class ErrorResponse {

    public final String errorCode;

    public final String reason;

    public ErrorResponse(String errorCode, String reason) {
        this.errorCode = errorCode;
        this.reason = reason;
    }
}
