package com.siddharthbaichoo.common.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.concurrent.TimeoutException;

@Entity
@Data                       // ✅ generates getters, setters, toString, equals, hashCode
@AllArgsConstructor         // ✅ generates all-args constructor
@NoArgsConstructor  
public class Payment {
    @Id
    private String id;

    private long amount;
    private String currency;
    private String status;

    @Column(name = "ts_epoch_ms")
    private Instant ts;
}
