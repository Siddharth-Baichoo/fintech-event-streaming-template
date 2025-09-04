package com.siddharthbaichoo.ingestion;

import com.siddharthbaichoo.avro.PaymentEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.Instant;
import java.util.UUID;

@SpringBootApplication
public class IngestionApplication {

  @Value("${app.kafka.topic}")
  private String topic;

  public static void main(String[] args) {
    SpringApplication.run(IngestionApplication.class, args);
  }

  @Bean
  CommandLineRunner runner(KafkaTemplate<String, PaymentEvent> template) {
    return args -> {
      for (int i = 0; i < 5; i++) {
        var evt = PaymentEvent.newBuilder()
            .setId(UUID.randomUUID().toString())
            .setAmount(100L + i)
            .setCurrency("CAD")
            .setStatus("PENDING")
            .setTs(Instant.now())
            .build();

        template.send(topic, evt.getId(), evt);
        System.out.println("[ingestion] sent: " + evt);
      }
    };
  }
}
