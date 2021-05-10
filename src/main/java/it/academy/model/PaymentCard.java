package it.academy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
