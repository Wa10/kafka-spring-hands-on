package br.com.springkafka.producer;

import br.com.springkafka.People;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PeopleProducer {

    private final String topicName;
    private final KafkaTemplate<String, People> kafkaTemplate;

    public PeopleProducer(@Value("${topic.name}") String topicName, KafkaTemplate<String, People> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
    }

    public void sendMessage(People people) {
        log.info("Sending message to topic: {}", topicName);
        kafkaTemplate.send(topicName, people)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        log.info("Mensagem enviada com sucesso para o tópico {} | Mensagem: {}",
                                topicName, people);
                        log.info("Partition: {}, Offset: {}",
                                result.getRecordMetadata().partition(),
                                result.getRecordMetadata().offset());
                    } else {
                        log.error("Erro ao enviar mensagem para o tópico {} | Mensagem: {}",
                                topicName, people);
                        log.error("Erro: ", ex);
                    }
                });
    }
}
