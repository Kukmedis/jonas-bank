package lt.keturka.jonasbank.resources;

import lt.keturka.jonasbank.api.AccountApiModel;
import lt.keturka.jonasbank.api.IdContainer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/accounts")
@Produces(MediaType.APPLICATION_JSON)
public class AccountsResource {

    @GET
    public List<AccountApiModel> getAllAccounts() {
        throw new UnsupportedOperationException();
    }

    @GET
    @Path("{accountId}")
    public AccountApiModel getAccount(String accountId) {
        throw new UnsupportedOperationException();
    }

    @POST
    public IdContainer openAccount() {
        throw new UnsupportedOperationException();
    }

}
