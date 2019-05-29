package br.com.breiv.payment.bankslip

import br.com.breiv.payment.Payment.BankSlipPayment
import br.com.breiv.payment.bankslip.exceptions.BankSlipPaymentNotFoundException
import br.com.breiv.payment.bankslip.gateways.BankSlipPaymentRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.only
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.github.benas.randombeans.api.EnhancedRandom.random
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test

class BankSlipPaymentSearchInteractorTest {

    private val repository: BankSlipPaymentRepository = mock()

    private val interactor = BankSlipPaymentSearchInteractor(
        repository
    )

    @Test
    fun `should throw exception when bank slip payment is not found`() {
        val id = random(Long::class.java)

        assertThatExceptionOfType(BankSlipPaymentNotFoundException::class.java)
            .isThrownBy { interactor.findBy(id) }
            .withMessage("Bank slip payment $id not found")
    }

    @Test
    fun `should find bank slip payment`() {
        val id = random(Long::class.java)

        val payment = random(BankSlipPayment::class.java)
        val expected = payment.copy()

        whenever(repository.findBy(id)).thenReturn(payment)

        val result = interactor.findBy(id)

        assertThat(result).isEqualTo(expected)

        verify(repository, only()).findBy(id)
    }
}