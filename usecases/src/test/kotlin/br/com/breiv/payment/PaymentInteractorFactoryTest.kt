package br.com.breiv.payment

import br.com.breiv.payment.PaymentType.CREDIT_CARD
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test

class PaymentInteractorFactoryTest {

    private val paymentRegisterInteractor: PaymentRegisterInteractor = mock()
    private val paymentSearchInteractor: PaymentSearchInteractor = mock()

    private val factory = PaymentInteractorFactory(
        listOf(paymentRegisterInteractor),
        listOf(paymentSearchInteractor)
    )

    @Test
    fun `should return payment register interactor`() {
        val paymentType = CREDIT_CARD

        whenever(paymentRegisterInteractor.paymentType).thenReturn(paymentType)

        val result = factory.getPaymentRegisterInteractorBy(paymentType)

        assertThat(result).isNotNull
    }

    @Test
    fun `should throw exception when payment register interactor is not found`() {
        val paymentType = CREDIT_CARD

        assertThatExceptionOfType(RuntimeException::class.java)
            .isThrownBy { factory.getPaymentRegisterInteractorBy(paymentType) }
            .withMessage("No payment register interactor found for $paymentType")
    }

    @Test
    fun `should return payment search interactor`() {
        whenever(paymentSearchInteractor.paymentType).thenReturn(CREDIT_CARD)

        val result = factory.getPaymentSearchInteractorBy(CREDIT_CARD)

        assertThat(result).isNotNull
    }

    @Test
    fun `should throw exception when payment search interactor is not found`() {
        val paymentType = CREDIT_CARD

        assertThatExceptionOfType(RuntimeException::class.java)
            .isThrownBy { factory.getPaymentSearchInteractorBy(paymentType) }
            .withMessage("No payment search interactor found for $paymentType")
    }
}