package it.academy.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "T_PAYMENT_CARD")
public class PaymentCard implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private String number;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private Currency currency;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private Type type;

    private Long clientId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "PaymentCard{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", currency=" + currency +
                ", type=" + type +
                ", clientId=" + clientId +
                '}';
    }
}
