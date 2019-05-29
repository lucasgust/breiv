package br.com.breiv.payment

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("payment")
class PaymentController(
    private val paymentInteractorFactory: PaymentInteractorFactory
) {

    @PostMapping
    fun register(@RequestBody payload: PaymentRegisterPayload): ResponseEntity<PaymentProcessResponse> {
        val response = paymentInteractorFactory.getPaymentRegisterInteractorBy(payload.paymentType).register(payload)

        return ResponseEntity.ok(response)
    }

    @GetMapping
    fun findBy(@RequestParam("paymentType") paymentType: PaymentType, @RequestParam("id") id: Long): ResponseEntity<Payment> {
        val response = paymentInteractorFactory.getPaymentSearchInteractorBy(paymentType).findBy(id)

        return ResponseEntity.ok(response)
    }
}