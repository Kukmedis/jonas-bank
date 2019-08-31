package lt.keturka.jonasbank.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenAccountRequestApiModel {

    public final String holderName;

    public OpenAccountRequestApiModel(@NotNull String holderName) {
        this.holderName = holderName;
    }
}
