package br.com.breiv.payment.gateways

import br.com.breiv.buyer.Buyer
import br.com.breiv.client.Client
import br.com.breiv.misc.config.PersistenceTestCase
import br.com.breiv.payment.Payment.BankSlipPayment
import br.com.breiv.payment.PaymentStatusType.APPROVED
import io.github.benas.randombeans.api.EnhancedRandom.random
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class BankSlipPaymentRepositoryImplTest : PersistenceTestCase() {

    @Autowired
    lateinit var bankSlipPaymentRepositoryImpl: BankSlipPaymentRepositoryImpl

    @Test
    fun `should save bank slip payment`() {
        val payment = random(BankSlipPayment::class.java)

        val expected = payment.copy()

        val result = bankSlipPaymentRepositoryImpl.save(payment)

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should find bank slip payment`() {
        setUpPersistence("sql/payment/BankSlipPayment.sql")

        val id = 1L

        val expected = BankSlipPayment(
            status = APPROVED,
            amount = 123.45,
            client = Client(id = 51L),
            buyer = Buyer(
                name = "Adriano Ribeiro",
                email = "amo@rock.com",
                cpf = "12345678901"
            ),
            number = "1234567890"
        )

        val result = bankSlipPaymentRepositoryImpl.findBy(id)

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should not find bank slip payment`() {
        val id = 99L

        val result = bankSlipPaymentRepositoryImpl.findBy(id)

        assertThat(result).isNull()
    }
}