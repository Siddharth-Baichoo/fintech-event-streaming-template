package com.siddharthbaichoo.streaming;

import com.siddharthbaichoo.avro.PaymentEvent;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class StreamsTopology {

  @Value("${spring.kafka.properties.schema.registry.url}")
  private String schemaRegistryUrl;

  private SpecificAvroSerde<PaymentEvent> paymentSerde(boolean isKey) {
    var serde = new SpecificAvroSerde<PaymentEvent>();
    Map<String, Object> cfg = new HashMap<>();
    cfg.put("schema.registry.url", schemaRegistryUrl);
    serde.configure(cfg, isKey);
    return serde;
  }

  @Bean
  public KStream<String, PaymentEvent> paymentsPipeline(StreamsBuilder builder) {
    System.out.println("[streams] wiring topology…");

    var keySerde = Serdes.String();
    var valSerde = paymentSerde(false);

    KStream<String, PaymentEvent> in =
        builder.stream("payments.raw", Consumed.with(keySerde, valSerde));

    in.peek((k, v) -> System.out.println("[streams] got " + v))
      .to("payments.enriched", Produced.with(keySerde, valSerde));

    return in; // <-- Returning KStream lets Spring know there is a source
  }
}