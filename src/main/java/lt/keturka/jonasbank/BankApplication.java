package lt.keturka.jonasbank;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.zalando.jackson.datatype.money.MoneyModule;

public class BankApplication extends Application<BankConfiguration> {

    @Override
    public void run(BankConfiguration bankConfiguration, Environment environment) throws Exception {
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
