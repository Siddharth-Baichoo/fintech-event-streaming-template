package com.siddharthbaichoo.api.web;

import com.siddharthbaichoo.avro.PaymentEvent;
import com.siddharthbaichoo.common.domain.Payment;
import com.siddharthbaichoo.api.repo.PaymentRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

  private final PaymentRepository repo;

  public PaymentController(PaymentRepository repo) {
    this.repo = repo;
  }

  // Kafka -> DB
  @KafkaListener(
      topics = "payments.enriched",
      groupId = "${spring.kafka.consumer.group-id}",
      containerFactory = "kafkaListenerContainerFactory")
  public void onPayment(PaymentEvent evt) {
    Payment p = new Payment(
        evt.getId(),
        evt.getAmount(),
        evt.getCurrency().toString(),   // if Avro enum/Utf8, adjust toString()
        evt.getStatus().toString(),
        evt.getTs()
    );
    repo.save(p);
    System.out.println("[api] saved " + p.getId());
  }

  // REST: list all
  @GetMapping
  public List<Payment> all() {
    return repo.findAll();
  }

  // REST: get by id
  @GetMapping("/{id}")
  public Payment byId(@PathVariable String id) {
    return repo.findById(id).orElseThrow();
  }
}
