package lt.keturka.jonasbank.resources;

import lt.keturka.jonasbank.api.IdContainer;
import lt.keturka.jonasbank.api.TransferRequestApiModel;
import lt.keturka.jonasbank.api.TransferResponseApiModel;
import lt.keturka.jonasbank.core.TransferProcessor;
import lt.keturka.jonasbank.core.domain.MoneyTransfer;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Path("/transfers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransfersResource {

    private final TransferProcessor transferProcessor;

    private final Map<String, MoneyTransfer> transferRepository;

    public TransfersResource(TransferProcessor transferProcessor, Map<String, MoneyTransfer> transferRepository) {
        this.transferProcessor = transferProcessor;
        this.transferRepository = transferRepository;
    }

    @POST
    public Response transferMoney(@NotNull @Valid TransferRequestApiModel transferRequestApiModel) {
        return Response.status(201).entity(
                new IdContainer(
                        transferProcessor.transferMoney(
                                transferRequestApiModel.getAmount(),
                                transferRequestApiModel.getDebitAccountId(),
                                transferRequestApiModel.getCreditAccountId())
                )).build();
    }

    @GET
    @Path("{transferId}")
    public TransferResponseApiModel getTransfer(@PathParam("transferId") String transferId) {
        return Optional.ofNullable(transferRepository.get(transferId)).map(toApiModel())
                .orElseThrow(NotFoundException::new);
    }

    @GET
    public List<TransferResponseApiModel> getAllTransfers() {
        return transferRepository.values().stream().map(toApiModel()).collect(Collectors.toList());
    }

    private Function<MoneyTransfer, TransferResponseApiModel> toApiModel() {
        return moneyTransfer -> new TransferResponseApiModel(
                moneyTransfer.id, moneyTransfer.amount, moneyTransfer.debitAccountId,
                moneyTransfer.creditAccountId, moneyTransfer.transferDate);
    }

}
