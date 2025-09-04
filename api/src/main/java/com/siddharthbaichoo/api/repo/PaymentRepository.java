package com.siddharthbaichoo.api.repo;

import com.siddharthbaichoo.common.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, String> { }
