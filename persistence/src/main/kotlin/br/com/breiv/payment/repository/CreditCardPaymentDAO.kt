package br.com.breiv.payment.repository

import br.com.breiv.payment.entity.CreditCardPaymentJPA
import org.springframework.data.jpa.repository.JpaRepository

interface CreditCardPaymentDAO : JpaRepository<CreditCardPaymentJPA, Long>