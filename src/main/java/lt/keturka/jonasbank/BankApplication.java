package lt.keturka.jonasbank;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import lt.keturka.jonasbank.api.ErrorResponse;
import lt.keturka.jonasbank.core.TransferProcessor;
import lt.keturka.jonasbank.core.domain.Account;
import lt.keturka.jonasbank.core.domain.MoneyTransfer;
import lt.keturka.jonasbank.exceptions.InsufficientFundsException;
import lt.keturka.jonasbank.exceptions.NotFoundException;
import lt.keturka.jonasbank.exceptions.TransferAccountNotFoundException;
import lt.keturka.jonasbank.exceptions.UnsupportedCurrencyException;
import lt.keturka.jonasbank.healthchecks.BankHealthCheck;
import lt.keturka.jonasbank.resources.AccountsResource;
import lt.keturka.jonasbank.resources.TransfersResource;
import org.zalando.jackson.datatype.money.MoneyModule;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.LinkedHashMap;
import java.util.Map;

public class BankApplication extends Application<BankConfiguration> {

    public static void main(String[] args) throws Exception {
        new BankApplication().run("server");
    }

    @Override
    public void run(BankConfiguration bankConfiguration, Environment environment) throws Exception {
        environment.jersey().register(new NotFoundExceptionMapper());
        environment.jersey().register(new UnsupportedCurrencyExceptionMapper());
        environment.jersey().register(new InsufficientFundsExceptionMapper());
        environment.jersey().register(new TransferAccountNotFoundExceptionMapper());
        Map<String, MoneyTransfer> transferRepository = new LinkedHashMap<>();
        Map<String, Account> accountRepository = new LinkedHashMap<>();
        environment.jersey().register(new TransfersResource(
                new TransferProcessor(transferRepository, accountRepository), transferRepository));
        environment.jersey().register(new AccountsResource(accountRepository));
    }

    @Override
    public String getName() {
        return "jonas-bank";
    }

    @Override
    public void initialize(Bootstrap<BankConfiguration> bootstrap) {
        super.initialize(bootstrap);
        bootstrap.getObjectMapper().registerModule(new MoneyModule());
        bootstrap.getHealthCheckRegistry().register("bank", new BankHealthCheck());
    }

    public static class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
        @Override
        public Response toResponse(NotFoundException e) {
            return Response.status(404).entity(
                    new ErrorResponse("ERR-404", "Entity not found")).build();
        }
    }

    public static class UnsupportedCurrencyExceptionMapper implements ExceptionMapper<UnsupportedCurrencyException> {
        @Override
        public Response toResponse(UnsupportedCurrencyException e) {
            return Response.status(422).entity(
                    new ErrorResponse("ERR-001", "Only EUR currency supported")).build();
        }
    }

    public static class InsufficientFundsExceptionMapper implements ExceptionMapper<InsufficientFundsException> {
        @Override
        public Response toResponse(InsufficientFundsException e) {
            return Response.status(422).entity(
                    new ErrorResponse("ERR-002", "Insufficient funds")).build();
        }
    }

    public static class TransferAccountNotFoundExceptionMapper
            implements ExceptionMapper<TransferAccountNotFoundException> {
        @Override
        public Response toResponse(TransferAccountNotFoundException e) {
            return Response.status(422).entity(
                    new ErrorResponse("ERR-003", "Cannot find account: " + e.accountId)).build();
        }
    }

}
