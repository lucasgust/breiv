package br.com.breiv.buyer.repository

import br.com.breiv.buyer.entity.BuyerJPA
import org.springframework.data.jpa.repository.JpaRepository

interface BuyerDAO : JpaRepository<BuyerJPA, Long>