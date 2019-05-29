package br.com.breiv.client.repository

import br.com.breiv.client.entity.ClientJPA
import org.springframework.data.jpa.repository.JpaRepository

interface ClientDAO : JpaRepository<ClientJPA, Long>