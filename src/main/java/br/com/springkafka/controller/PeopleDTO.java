package br.com.springkafka.controller;

import java.util.List;


public record PeopleDTO(
        String name,
        String cpf,
        List<String> books
) {}

