package lt.keturka.jonasbank.api;

import javax.money.MonetaryAmount;

public class AccountApiModel {

    public final String id;

    public final String holderName;

    public final MonetaryAmount balance;

    public AccountApiModel(String id, String holderName, MonetaryAmount balance) {
        this.id = id;
        this.holderName = holderName;
        this.balance = balance;
    }
}
