package lt.keturka.jonasbank.api;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.money.MonetaryAmount;

public class AccountApiModel {

    public final String id;

    public final MonetaryAmount balance;

    public AccountApiModel(String id, MonetaryAmount balance) {
        this.id = id;
        this.balance = balance;
    }
}
