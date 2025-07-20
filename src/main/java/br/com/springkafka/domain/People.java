package br.com.springkafka.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class People {

    @Id
    private String id;
    private String name;
    private String cpf;

    @OneToMany(mappedBy = "people", cascade = CascadeType.ALL)
    private List<Book> books;

    public People(br.com.springkafka.People people) {
        this.id = people.getId().toString();
        this.name = people.getName().toString();
        this.cpf = people.getCpf().toString();
        this.books = people.getBooks().stream()
                .map(book -> Book.builder()
                        .people(this)
                        .name(book.toString())
                        .build()).collect(Collectors.toList());
    }
}
