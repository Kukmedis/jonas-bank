package lt.keturka.jonasbank.resources;

import lt.keturka.jonasbank.api.IdContainer;
import lt.keturka.jonasbank.api.TransferRequestApiModel;
import lt.keturka.jonasbank.api.TransferResponseApiModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/transfers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransfersResource {

    @POST
    public IdContainer transferMoney(TransferRequestApiModel transferRequestApiModel) {
        throw new UnsupportedOperationException();
    }

    @GET
    @Path("transferId")
    public TransferResponseApiModel getTransfer(String transferId) {
        throw new UnsupportedOperationException();
    }

    @GET
    public List<TransferResponseApiModel> getAllTransfers() {
        throw new UnsupportedOperationException();
    }

}
