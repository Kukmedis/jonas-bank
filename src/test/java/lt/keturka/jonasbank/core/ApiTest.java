package lt.keturka.jonasbank.core;

import io.dropwizard.testing.junit.DropwizardAppRule;
import io.restassured.http.ContentType;
import lt.keturka.jonasbank.BankApplication;
import lt.keturka.jonasbank.BankConfiguration;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class ApiTest {

    @ClassRule
    public static final DropwizardAppRule<BankConfiguration> RULE =
            new DropwizardAppRule<>(BankApplication.class);

    private String url;

    @Before
    public void setUp() throws Exception {
        url = "http://localhost:" + RULE.getLocalPort();
    }

    @Test
    public void shouldOpenAnAccount() {
        String accountId = given()
                .body("{\"holderName\" : \"TEST_NAME\"}")
                .contentType(ContentType.JSON)
                .when()
                .post(url + "/accounts")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(201)
                .body("id", notNullValue())
                .extract().jsonPath().getString("id");

        get(url + "/accounts/" + accountId)
                .then()
                .contentType(ContentType.JSON)
                .body("holderName", equalTo("TEST_NAME"))
                .body("balance.amount", equalTo(0.0f))
                .body("balance.currency", equalTo("EUR"));

        get(url + "/accounts")
                .then()
                .contentType(ContentType.JSON)
                .body("[1].holderName", equalTo("TEST_NAME"))
                .body("[1].balance.amount", equalTo(0.0f))
                .body("[1].balance.currency", equalTo("EUR"))
                .body("[1].id", equalTo(accountId));
    }

    @Test
    public void shouldCreateATransfer() {
        String accountId = given()
                .body("{\"holderName\" : \"TEST_NAME\"}")
                .contentType(ContentType.JSON)
                .when()
                .post(url + "/accounts")
                .then()
                .extract().jsonPath().getString("id");

        String transferRequestBody =
                String.format(
                        "{\"amount\": {\"amount\": 10.0, \"currency\": \"EUR\"}, " +
                        "\"debitAccountId\": \"%s\", " +
                        "\"creditAccountId\": \"%s\"}",
                        "house", accountId);

        String transferId = given()
                .body(transferRequestBody)
                .contentType(ContentType.JSON)
                .when()
                .post(url + "/transfers")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(201)
                .body("id", notNullValue())
                .extract().jsonPath().getString("id");

        get(url + "/transfers/" + transferId)
                .then()
                .contentType(ContentType.JSON)
                .body("debitAccountId", equalTo("house"))
                .body("creditAccountId", equalTo(accountId))
                .body("amount.amount", equalTo(10.0f))
                .body("amount.currency", equalTo("EUR"));

        get(url + "/transfers")
                .then()
                .contentType(ContentType.JSON)
                .body("[0].debitAccountId", equalTo("house"))
                .body("[0].creditAccountId", equalTo(accountId))
                .body("[0].amount.amount", equalTo(10.0f))
                .body("[0].amount.currency", equalTo("EUR"))
                .body("[0].id", equalTo(transferId));

        get(url + "/accounts")
                .then()
                .contentType(ContentType.JSON)
                .body("[0].balance.amount", equalTo(9999990.0f))
                .body("[0].id", equalTo("house"))
                .body("[2].balance.amount", equalTo(10.0f))
                .body("[2].id", equalTo(accountId));
    }
}
