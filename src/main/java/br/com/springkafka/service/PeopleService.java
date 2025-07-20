package br.com.springkafka.service;

import br.com.springkafka.People;
import br.com.springkafka.controller.PeopleDTO;
import br.com.springkafka.producer.PeopleProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PeopleService {

    private final PeopleProducer peopleProducer;

    public void sendMessage(PeopleDTO peopleDTO){
        var id = UUID.randomUUID().toString();

        var message = People.newBuilder()
                .setId(id)
                .setName(peopleDTO.name())
                .setCpf(peopleDTO.cpf())
                .setBooksd(peopleDTO.books().stream().map(p -> (CharSequence) p).collect(Collectors.toList()))
                .build();

        peopleProducer.sendMessage(message);

    }
}
