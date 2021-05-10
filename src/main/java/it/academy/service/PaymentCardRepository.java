package it.academy.service;

import it.academy.model.Currency;
import it.academy.model.PaymentCard;
import it.academy.model.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentCardRepository extends JpaRepository<PaymentCard, Long> {

    List<PaymentCard> findByClientIdNotNullAndTypeAndCurrency(
            Type type, Currency currency, Pageable var1);
}
