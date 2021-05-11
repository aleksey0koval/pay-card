package it.academy.rest;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

public class PaymentCardUpdateCmd implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Positive
    private Long clientId;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
