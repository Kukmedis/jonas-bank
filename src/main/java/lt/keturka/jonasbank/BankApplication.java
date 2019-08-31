package lt.keturka.jonasbank;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import lt.keturka.jonasbank.api.ErrorResponse;
import lt.keturka.jonasbank.exceptions.InsufficientFundsException;
import lt.keturka.jonasbank.exceptions.TransferAccountNotFoundException;
import lt.keturka.jonasbank.exceptions.UnsupportedCurrencyException;
import org.zalando.jackson.datatype.money.MoneyModule;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class BankApplication extends Application<BankConfiguration> {

    @Override
    public void run(BankConfiguration bankConfiguration, Environment environment) throws Exception {
        environment.jersey().register(
                (ExceptionMapper<NotFoundException>) throwable -> Response.status(404).entity(
                        new ErrorResponse("ERR-404", "Entity not found")).build());
        environment.jersey().register(
                (ExceptionMapper<UnsupportedCurrencyException>) throwable ->
                        Response.status(422).entity(
                                new ErrorResponse("ERR-001", "Only EUR currency supported")).build());
        environment.jersey().register(
                (ExceptionMapper<InsufficientFundsException>) throwable ->
                        Response.status(422).entity(
                                new ErrorResponse("ERR-002", "Insufficient funds")).build());
        environment.jersey().register(
                (ExceptionMapper<TransferAccountNotFoundException>) throwable ->
                        Response.status(422).entity(
                                new ErrorResponse("ERR-003", "Cannot find account: " + throwable.accountId)).build());
        new BankApplication().run(bankConfiguration, environment);
    }

    @Override
    public String getName() {
        return "jonas-bank";
    }

    @Override
    public void initialize(Bootstrap<BankConfiguration> bootstrap) {
        super.initialize(bootstrap);
        bootstrap.getObjectMapper().registerModule(new MoneyModule());
    }
}
