package br.com.breiv.creditcard.repository

import br.com.breiv.creditcard.entity.CreditCardJPA
import org.springframework.data.jpa.repository.JpaRepository

interface CreditCardDAO : JpaRepository<CreditCardJPA, Long>