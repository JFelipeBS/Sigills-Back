package br.edu.ufersa.pw.sigillsback.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ufersa.pw.sigillsback.DTO.CreditCardDto;
import br.edu.ufersa.pw.sigillsback.entity.CreditCard;
import br.edu.ufersa.pw.sigillsback.repository.CreditCardRepository;

@Service
public class CreditCardService {

    @Autowired
    private CreditCardRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;

    public List<CreditCardDto> findAll(){
        List<CreditCardDto> list = new ArrayList<CreditCardDto>();
        List<CreditCard> userCards = repository.findByUser(userService.currentUser());

        for (CreditCard creditCard : userCards){
            list.add(mapper.map(creditCard, CreditCardDto.class));
        }
        
        return list;
    }

    public Optional<CreditCardDto> findById(String id){
        Optional<CreditCard> creditCard = repository.findById(Long.valueOf(id));
        if (creditCard.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of( mapper.map(creditCard.get(),CreditCardDto.class));
    }

    public CreditCardDto add(CreditCard creditCard){
        CreditCard card = new CreditCard();
        card.setName(creditCard.getName());
        card.setUser(userService.currentUser());
        card.setDueDate(creditCard.getDueDate());
        card.setClosingDate(creditCard.getClosingDate());
        return mapper.map(repository.save(card),CreditCardDto.class);
    }

    public Optional<CreditCardDto> update(String id, CreditCard creditCardReceived){
        Optional<CreditCard> credit_card = repository.findById(Long.valueOf(id));

        if (credit_card.isEmpty()) {
            return Optional.empty();
        }

        credit_card.get().setName(creditCardReceived.getName());
        credit_card.get().setDueDate(creditCardReceived.getDueDate());
        credit_card.get().setClosingDate(creditCardReceived.getClosingDate());

        return Optional.of(mapper.map(repository.save(credit_card.get()),CreditCardDto.class));
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteByName(CreditCardDto creditCard){
        repository.deleteById(creditCard.getId());
    }
    
}
