package br.com.breiv.payment.repository

import br.com.breiv.payment.entity.BankSlipPaymentJPA
import org.springframework.data.jpa.repository.JpaRepository

interface BankSlipPaymentDAO : JpaRepository<BankSlipPaymentJPA, Long>