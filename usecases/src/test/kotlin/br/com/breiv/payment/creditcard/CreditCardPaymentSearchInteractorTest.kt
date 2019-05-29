package br.com.breiv.payment.creditcard

import br.com.breiv.payment.Payment.CreditCardPayment
import br.com.breiv.payment.creditcard.exceptions.CreditCardPaymentNotFoundException
import br.com.breiv.payment.creditcard.gateways.CreditCardPaymentRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.only
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.github.benas.randombeans.api.EnhancedRandom.random
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test

class CreditCardPaymentSearchInteractorTest {

    private val repository: CreditCardPaymentRepository = mock()

    private val interactor = CreditCardPaymentSearchInteractor(
        repository
    )

    @Test
    fun `should throw exception when credit card payment is not found`() {
        val id = random(Long::class.java)

        assertThatExceptionOfType(CreditCardPaymentNotFoundException::class.java)
            .isThrownBy { interactor.findBy(id) }
            .withMessage("Credit Card payment $id not found")
    }

    @Test
    fun `should find credit card payment`() {
        val id = random(Long::class.java)

        val payment = random(CreditCardPayment::class.java)
        val expected = payment.copy()

        whenever(repository.findBy(id)).thenReturn(payment)

        val result = interactor.findBy(id)

        assertThat(result).isEqualTo(expected)

        verify(repository, only()).findBy(id)
    }
}