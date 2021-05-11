package it.academy.rest;

import it.academy.model.Client;
import it.academy.model.PaymentCard;
import it.academy.service.ClientRepository;
import it.academy.service.PaymentCardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PaymentCardController {

    private final PaymentCardRepository paymentCardRepository;
    private final ClientRepository clientRepository;

    public PaymentCardController(PaymentCardRepository paymentCardRepository,
                                 ClientRepository clientRepository) {
        this.paymentCardRepository = paymentCardRepository;
        this.clientRepository = clientRepository;
    }

    @GetMapping("/cards")
    public List<PaymentCard> allPaymentCard() {
        return paymentCardRepository.findAll();
    }

    @PostMapping("/cards")
    public ResponseEntity<PaymentCard> addCard(@Valid @RequestBody PaymentCard card) {
        PaymentCard addedCard;
        try {
            if (card.getClientId() != null
                    && !clientRepository.existsById(card.getClientId())) {
                return new ResponseEntity(
                        "Client is not found: id=" + card.getClientId(),
                        HttpStatus.BAD_REQUEST);
            }
            addedCard = paymentCardRepository.save(card);
        } catch (Exception exception) {
            return new ResponseEntity(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(addedCard);
    }

    @PutMapping("/cards/{id}")
    @Transactional
    public ResponseEntity<PaymentCard> updateCard(
            @PathVariable Long id,
            @Valid @RequestBody PaymentCardUpdateCmd updateCmd
    ) {
        PaymentCard updatedPaymentCard;
        try {
            PaymentCard savedPaymentCard = paymentCardRepository
                    .findById(id)
                    .orElseThrow(() -> new Exception("Payment card is not found: id=" + id));
            Client savedClient = clientRepository
                    .findById(updateCmd.getClientId())
                    .orElseThrow(() -> new Exception("Client is not found: id=" + updateCmd.getClientId()));

            savedClient.setStatus(savedPaymentCard.getType().toString());
            savedPaymentCard.setClientId(savedClient.getId());

            clientRepository.save(savedClient);
            updatedPaymentCard = paymentCardRepository.save(savedPaymentCard);

        } catch (Exception exception) {
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(updatedPaymentCard);
    }
}
