package it.academy.controller;

import it.academy.model.Client;
import it.academy.model.Currency;
import it.academy.model.PaymentCard;
import it.academy.model.Type;
import it.academy.service.ClientRepository;
import it.academy.service.PaymentCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class ClientController {

    private ClientRepository clientRepository;
    private PaymentCardRepository paymentCardRepository;

    @Autowired
    public ClientController(ClientRepository clientRepository,
                            PaymentCardRepository paymentCardRepository) {
        this.clientRepository = clientRepository;
        this.paymentCardRepository = paymentCardRepository;
    }

    @GetMapping("/clients")
    public List<Client> allClient() {
        return clientRepository.findAll();
    }

    @PostMapping("/clients")
    public ResponseEntity<Client> addClient(@Valid @RequestBody Client client) {

        Client addedClient;

        try {
            addedClient = clientRepository.save(client);
        } catch (Exception exception) {
            return new ResponseEntity(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(addedClient);
    }

    @GetMapping("/clients/phones")
    public List<String> getPhone(
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNum,
            @RequestParam Type type,
            @RequestParam Currency currency
    ) {
        return paymentCardRepository
                .findByClientIdNotNullAndTypeAndCurrency(type,
                        currency,
                        PageRequest.of(pageNum, pageSize))
                .stream()
                .map(PaymentCard::getClientId)
                .map(id -> clientRepository.findById(id).orElse(null))
                .filter(Objects::isNull)
                .map(Client::getPhoneNumber)
                .collect(Collectors.toList());
    }


}
