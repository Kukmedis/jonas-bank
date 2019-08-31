package lt.keturka.jonasbank.resources;

import lt.keturka.jonasbank.api.TransactionApiModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/transactions")
@Produces(MediaType.APPLICATION_JSON)
public class TransactionsResource {

    @GET
    public List<TransactionApiModel> getTransactions(
            @QueryParam("debitAccountId") String debitAccountId,
            @QueryParam("creditAccountId") String creditAccountId,
            @QueryParam("accountId") String accountId) {
        throw new UnsupportedOperationException();
    }

    @GET
    @Path("{transactionId}")
    public TransactionApiModel getTransaction(String transactionId) {
        throw new UnsupportedOperationException();
    }

}
