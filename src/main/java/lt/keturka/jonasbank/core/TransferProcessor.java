package lt.keturka.jonasbank.core;

import lt.keturka.jonasbank.core.domain.Account;
import lt.keturka.jonasbank.core.domain.MoneyTransfer;
import lt.keturka.jonasbank.exceptions.SameTransferAccountException;
import lt.keturka.jonasbank.exceptions.TransferAccountNotFoundException;
import lt.keturka.jonasbank.exceptions.InsufficientFundsException;
import lt.keturka.jonasbank.exceptions.UnsupportedCurrencyException;

import javax.money.MonetaryAmount;
import java.time.Clock;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/*
Transfer processor is responsible for validating transfers, and transferring funds between accounts.
 */
public class TransferProcessor {

    private final Map<String, MoneyTransfer> transferRepository;

    private final Map<String, Account> accountRepository;

    private final Clock clock;

    public TransferProcessor(Map<String, MoneyTransfer> transferRepository, Map<String, Account> accountRepository) {
        this(transferRepository, accountRepository, Clock.systemUTC());
    }

    /*
    To be used in tests. Clock can be provided (eg. FixedClock) in order to properly test time sensitive functionality
     */
    public TransferProcessor(
            Map<String, MoneyTransfer> transferRepository, Map<String, Account> accountRepository, Clock clock) {
        this.transferRepository = transferRepository;
        this.accountRepository = accountRepository;
        this.clock = clock;
    }

    public synchronized String transferMoney(MonetaryAmount amount, String debitAccountId, String creditAccountId) {
        Account debitAccount = getCreditAccount(debitAccountId);
        Account creditAccount = getCreditAccount(creditAccountId);
        if (debitAccountId.equals(creditAccountId)) {
            throw new SameTransferAccountException();
        }
        if (!"EUR".equals(amount.getCurrency().getCurrencyCode())) {
            throw new UnsupportedCurrencyException();
        }
        if (amount.compareTo(debitAccount.getBalance()) > 0) {
            throw new InsufficientFundsException();
        }
        debitAccount.setBalance(debitAccount.getBalance().subtract(amount));
        creditAccount.setBalance(creditAccount.getBalance().add(amount));
        return saveTransfer(amount, debitAccountId, creditAccountId);
    }

    private Account getCreditAccount(String creditAccountId) {
        return Optional.ofNullable(accountRepository.get(creditAccountId)).orElseThrow(
                () -> new TransferAccountNotFoundException(creditAccountId));
    }

    private String saveTransfer(MonetaryAmount amount, String debitAccountId, String creditAccountId) {
        MoneyTransfer moneyTransfer = new MoneyTransfer(
                UUID.randomUUID().toString(), debitAccountId, creditAccountId, amount, Instant.now(clock));
        transferRepository.put(moneyTransfer.id, moneyTransfer);
        return moneyTransfer.id;
    }
}
