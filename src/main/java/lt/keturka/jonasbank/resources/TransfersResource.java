package lt.keturka.jonasbank.resources;

import lt.keturka.jonasbank.api.IdContainer;
import lt.keturka.jonasbank.api.TransferApiModel;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/transfers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransfersResource {

    @POST
    public IdContainer transferMoney(TransferApiModel transferApiModel) {
        throw new UnsupportedOperationException();
    }

}
