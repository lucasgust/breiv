package br.com.breiv.payment.gateways

import br.com.breiv.buyer.Buyer
import br.com.breiv.client.Client
import br.com.breiv.creditcard.CreditCard
import br.com.breiv.misc.config.PersistenceTestCase
import br.com.breiv.payment.Payment.CreditCardPayment
import br.com.breiv.payment.PaymentStatusType.REJECTED
import io.github.benas.randombeans.api.EnhancedRandom.random
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDate

class CreditCardPaymentRepositoryImplTest : PersistenceTestCase() {

    @Autowired
    lateinit var creditCardPaymentRepositoryImpl: CreditCardPaymentRepositoryImpl

    @Test
    fun `should save credit card payment`() {
        val payment = random(CreditCardPayment::class.java)

        val expected = payment.copy()

        val result = creditCardPaymentRepositoryImpl.save(payment)

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should find credit card payment`() {
        setUpPersistence("sql/payment/CreditCardPayment.sql")

        val id = 1L

        val expected = CreditCardPayment(
            status = REJECTED,
            amount = 123.45,
            client = Client(id = 51L),
            buyer = Buyer(
                name = "Adriano Ribeiro",
                email = "amo@rock.com",
                cpf = "12345678901"
            ),
            creditCard = CreditCard(
                holderName = "Dri Dri",
                number = "1234567890123456",
                expirationDate = LocalDate.of(2020, 12, 1),
                cvv = "666"
            )
        )

        val result = creditCardPaymentRepositoryImpl.findBy(id)

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should not find credit card payment`() {
        val id = 99L

        val result = creditCardPaymentRepositoryImpl.findBy(id)

        assertThat(result).isNull()
    }
}