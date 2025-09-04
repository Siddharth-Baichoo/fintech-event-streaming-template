package com.siddharthbaichoo.streaming;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopics {
  @Bean public NewTopic paymentsRaw()      { return new NewTopic("payments.raw", 1, (short)1); }
  @Bean public NewTopic paymentsEnriched() { return new NewTopic("payments.enriched", 1, (short)1); }
}
