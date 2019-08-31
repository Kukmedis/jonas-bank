package lt.keturka.jonasbank.core;

import lt.keturka.jonasbank.core.domain.Account;
import lt.keturka.jonasbank.core.domain.MoneyTransfer;
import lt.keturka.jonasbank.exceptions.TransferAccountNotFoundException;
import lt.keturka.jonasbank.exceptions.UnsupportedCurrencyException;
import org.hamcrest.*;
import org.javamoney.moneta.Money;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class TransferProcessorTest {

    private TransferProcessor transferProcessor;

    private HashMap<String, Account> accountRepository;

    private HashMap<String, MoneyTransfer> transferRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        accountRepository = new HashMap<String, Account>() {{
            put(createAccount(100.0, "ID_1").getId(), createAccount(100.0, "ID_1"));
            put(createAccount(50.0, "ID_2").getId(), createAccount(50.0, "ID_2"));
        }};
        transferRepository = new HashMap<>();
        transferProcessor = new TransferProcessor(
                transferRepository, accountRepository,
                Clock.fixed(Instant.ofEpochSecond(1000), ZoneId.systemDefault()));
    }

    @Test
    public void shouldInformWhenDebitAccountNotFound() {
        thrown.expect(TransferAccountNotFoundException.class);
        thrown.expect(new CustomTypeSafeMatcher<TransferAccountNotFoundException>("Id does not match") {
            @Override
            protected boolean matchesSafely(TransferAccountNotFoundException item) {
                return item.accountId.equals("MISSING");
            }
        });
        transferProcessor.transferMoney(Money.of(10.0, "EUR"), "MISSING", "ID_2");
    }

    @Test
    public void shouldInformWhenCreditAccountNotFound() {
        thrown.expect(TransferAccountNotFoundException.class);
        thrown.expect(new CustomTypeSafeMatcher<TransferAccountNotFoundException>("Id does not match") {
            @Override
            protected boolean matchesSafely(TransferAccountNotFoundException item) {
                return item.accountId.equals("MISSING");
            }
        });
        transferProcessor.transferMoney(Money.of(10.0, "EUR"), "ID_1", "MISSING");
    }

    @Test(expected = UnsupportedCurrencyException.class)
    public void shouldNotAllowNonEuroTransfers() {
        transferProcessor.transferMoney(Money.of(10.0, "GBP"), "ID_1", "ID_2");
    }

    @Test
    public void shouldMoveMoneyFromAccountToAccount() {
        transferProcessor.transferMoney(Money.of(10.0, "EUR"), "ID_1", "ID_2");
        assertThat(accountRepository.get("ID_1").getBalance(), equalTo(Money.of(90.0, "EUR")));
        assertThat(accountRepository.get("ID_2").getBalance(), equalTo(Money.of(60.0, "EUR")));
    }

    @Test
    public void shouldCreateATransferRecord() {
        String transferId = transferProcessor.transferMoney(Money.of(10.0, "EUR"), "ID_1", "ID_2");
        assertThat(transferRepository.get(transferId), equalTo(
                new MoneyTransfer(transferId, "ID_1", "ID_2", Money.of(10.0, "EUR"), Instant.ofEpochSecond(1000))));
    }

    private Account createAccount(double balance, String id) {
        Account testAccount2 = new Account();
        testAccount2.setBalance(Money.of(balance, "EUR"));
        testAccount2.setId(id);
        return testAccount2;
    }
}