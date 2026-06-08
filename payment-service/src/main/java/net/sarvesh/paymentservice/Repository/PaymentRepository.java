package net.sarvesh.paymentservice.Repository;

import net.sarvesh.paymentservice.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository
        extends JpaRepository<Payment, Long> {
}