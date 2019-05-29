package br.com.breiv.exceptions

import br.com.breiv.payment.bankslip.exceptions.BankSlipPaymentNotFoundException
import br.com.breiv.payment.creditcard.exceptions.CreditCardPaymentNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class PaymentSearchErrorAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BankSlipPaymentNotFoundException::class, CreditCardPaymentNotFoundException::class)
    fun handle(e: Exception) {
        logger.error(e.message, e)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(PaymentSearchErrorAdvice::class.java)!!
    }
}