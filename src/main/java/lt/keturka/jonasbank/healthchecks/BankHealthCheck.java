package lt.keturka.jonasbank.healthchecks;

import com.codahale.metrics.health.HealthCheck;

public class BankHealthCheck extends HealthCheck {
    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
