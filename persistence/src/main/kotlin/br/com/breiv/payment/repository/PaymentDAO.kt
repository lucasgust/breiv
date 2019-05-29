package br.com.breiv.payment.repository

import br.com.breiv.payment.entity.PaymentJPA
import org.springframework.data.jpa.repository.JpaRepository

interface PaymentDAO : JpaRepository<PaymentJPA, Long>