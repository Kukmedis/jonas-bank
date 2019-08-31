package lt.keturka.jonasbank.resources;

import lt.keturka.jonasbank.api.AccountApiModel;
import lt.keturka.jonasbank.api.IdContainer;
import lt.keturka.jonasbank.api.OpenAccountRequestApiModel;
import lt.keturka.jonasbank.core.domain.Account;
import org.javamoney.moneta.Money;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Path("/accounts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccountsResource {

    private final Map<String, Account> accountRepository;

    public AccountsResource(Map<String, Account> accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GET
    public List<AccountApiModel> getAllAccounts() {
        return accountRepository.values().stream().map(
                toApiModel()).collect(Collectors.toList());
    }

    @GET
    @Path("{accountId}")
    public AccountApiModel getAccount(@PathParam("accountId") String accountId) {
        return Optional.ofNullable(accountRepository.get(accountId)).map(toApiModel())
                .orElseThrow(NotFoundException::new);
    }

    @POST
    public IdContainer openAccount(OpenAccountRequestApiModel requestApiModel) {
        String id = UUID.randomUUID().toString();
        Account account = new Account();
        account.setId(id);
        account.setBalance(Money.of(0.0, "EUR"));
        account.setHolderName(requestApiModel.holderName);
        accountRepository.put(id, account);
        return new IdContainer(id);
    }

    private Function<Account, AccountApiModel> toApiModel() {
        return account -> new AccountApiModel(account.getId(), account.getHolderName(), account.getBalance());
    }

}
