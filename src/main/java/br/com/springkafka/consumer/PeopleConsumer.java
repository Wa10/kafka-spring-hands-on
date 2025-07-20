package br.com.springkafka.consumer;

import br.com.springkafka.People;
import br.com.springkafka.service.PeopleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PeopleConsumer {

    private final PeopleService peopleService;

    @KafkaListener(topics = "${topic.name}")
    public void consumeMessage(ConsumerRecord<String, People>  record, Acknowledgment ack) {
        var people = record.value();

        log.info("Consumindo mensagem: {}", people);

        br.com.springkafka.domain.People p = new br.com.springkafka.domain.People(people);
        peopleService.save(p);

        ack.acknowledge();
    }
}
