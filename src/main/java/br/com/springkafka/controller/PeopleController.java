package br.com.springkafka.controller;

import br.com.springkafka.service.PeopleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/peoples")
@RequiredArgsConstructor
public class PeopleController {

    private final PeopleService peopleService;

    @PostMapping
    public ResponseEntity<Void> sendMessage(@RequestBody PeopleDTO peopleDTO){
        peopleService.sendMessage(peopleDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
