package br.com.breiv.payment.bankslip

import br.com.breiv.payment.Payment.BankSlipPayment
import br.com.breiv.payment.PaymentProcessResponse.BankSlipPaymentProcessResponse
import br.com.breiv.payment.PaymentRegisterPayload
import br.com.breiv.payment.bankslip.gateways.BankSlipPaymentRepository
import br.com.breiv.payment.bankslip.gateways.BankSlipPaymentService
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.only
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.github.benas.randombeans.api.EnhancedRandom.random
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BankSlipPaymentRegisterInteractorTest {

    private val paymentService: BankSlipPaymentService = mock()
    private val repository: BankSlipPaymentRepository = mock()

    private val interactor = BankSlipPaymentRegisterInteractor(
        paymentService,
        repository
    )

    @Test
    fun `should register bank slip payment`() {
        val payload = random(PaymentRegisterPayload::class.java)
        val paymentResponse = random(BankSlipPaymentProcessResponse::class.java)

        val payment = BankSlipPayment(
            status = paymentResponse.status,
            amount = payload.amount,
            client = payload.client,
            buyer = payload.buyer,
            number = paymentResponse.number
        )

        val expected = paymentResponse.copy()

        whenever(paymentService.process(payload)).thenReturn(paymentResponse)
        whenever(repository.save(payment)).thenReturn(payment)

        val result = interactor.register(payload)

        assertThat(result).isEqualTo(expected)

        verify(paymentService, only()).process(payload)
        verify(repository, only()).save(payment)
    }
}