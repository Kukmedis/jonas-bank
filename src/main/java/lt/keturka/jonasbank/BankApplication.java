package lt.keturka.jonasbank;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class BankApplication extends Application<BankConfiguration> {

    @Override
    public void run(BankConfiguration bankConfiguration, Environment environment) throws Exception {
        new BankApplication().run(bankConfiguration, environment);
    }

    @Override
    public String getName() {
        return "jonas-bank";
    }
}
