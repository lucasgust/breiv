package br.com.breiv

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BreivApplication

fun main(args: Array<String>) {
    runApplication<BreivApplication>(*args)
}