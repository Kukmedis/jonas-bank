package lt.keturka.jonasbank.core.domain;

import javax.money.MonetaryAmount;

public class Account {

    private String id;

    private MonetaryAmount balance;

    private String holderName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MonetaryAmount getBalance() {
        return balance;
    }

    public void setBalance(MonetaryAmount balance) {
        this.balance = balance;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }
}
