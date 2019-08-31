package lt.keturka.jonasbank.resources;

import lt.keturka.jonasbank.api.AccountApiModel;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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

}
