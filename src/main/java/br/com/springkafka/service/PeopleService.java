package br.com.springkafka.service;

import br.com.springkafka.People;
import br.com.springkafka.controller.PeopleDTO;
import br.com.springkafka.producer.PeopleProducer;
import br.com.springkafka.repository.PeopleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PeopleService {

    private final PeopleProducer peopleProducer;
    private final PeopleRepository peopleRepository;

    public void save(br.com.springkafka.domain.People people){
        peopleRepository.save(people);
    }

    public void sendMessage(PeopleDTO peopleDTO){
        var id = UUID.randomUUID().toString();
        var message = People.newBuilder()
                .setId(id)
                .setName(peopleDTO.name())
                .setCpf(peopleDTO.cpf())
                .setBooks(peopleDTO.books().stream().map(p -> (CharSequence) p).collect(Collectors.toList()))
                .build();

        peopleProducer.sendMessage(message);

    }
}
