package br.com.breiv.payment

import br.com.breiv.buyer.Buyer
import br.com.breiv.client.Client
import br.com.breiv.creditcard.CreditCard
import br.com.breiv.misc.config.ApplicationTestCase
import br.com.breiv.payment.Payment.BankSlipPayment
import br.com.breiv.payment.Payment.CreditCardPayment
import br.com.breiv.payment.PaymentProcessResponse.BankSlipPaymentProcessResponse
import br.com.breiv.payment.PaymentProcessResponse.CreditCardPaymentProcessResponse
import br.com.breiv.payment.PaymentType.BANK_SLIP
import br.com.breiv.payment.PaymentType.CREDIT_CARD
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.github.benas.randombeans.api.EnhancedRandom.random
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import java.time.LocalDate


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PaymentControllerTest : ApplicationTestCase() {

    @LocalServerPort
    var port: Int = 0

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    val mapper = jacksonObjectMapper().registerModule(JavaTimeModule())

    @Test
    fun `should return bank slip payment process response`() {
        val payload = random(PaymentRegisterPayload::class.java).copy(paymentType = BANK_SLIP)

        val response = restTemplate.postForEntity(
            "http://localhost:$port/payment",
            payload,
            Any::class.java
        )

        val body = mapper.convertValue(response.body, BankSlipPaymentProcessResponse::class.java)

        assertThat(response.statusCodeValue).isEqualTo(200)
        assertThat(body).isNotNull
    }

    @Test
    fun `should return credit card payment process response`() {
        val payload = random(PaymentRegisterPayload::class.java).copy(paymentType = CREDIT_CARD)

        val response = restTemplate.postForEntity(
            "http://localhost:$port/payment",
            payload,
            Any::class.java
        )

        val body = mapper.convertValue(response.body, CreditCardPaymentProcessResponse::class.java)

        assertThat(response.statusCodeValue).isEqualTo(200)
        assertThat(body).isNotNull
    }

    @Test
    fun `should return bank slip payment`() {
        setUpPersistence("sql/payment/BankSlipPayment.sql")

        val payload = mapOf(
            "paymentType" to BANK_SLIP,
            "id" to 1L
        )

        val response = restTemplate.getForEntity(
            "http://localhost:$port/payment?paymentType={paymentType}&id={id}",
            Any::class.java,
            payload
        )

        val body = mapper.convertValue(response.body, BankSlipPayment::class.java)

        val expected = BankSlipPayment(
            status = PaymentStatusType.APPROVED,
            amount = 123.45,
            client = Client(id = 51L),
            buyer = Buyer(
                name = "Adriano Ribeiro",
                email = "amo@rock.com",
                cpf = "12345678901"
            ),
            number = "1234567890"
        )

        assertThat(response.statusCodeValue).isEqualTo(200)
        assertThat(body).isEqualTo(expected)
    }

    @Test
    fun `should return credit card payment`() {
        setUpPersistence("sql/payment/CreditCardPayment.sql")

        val payload = mapOf(
            "paymentType" to CREDIT_CARD,
            "id" to 1L
        )

        val response = restTemplate.getForEntity(
            "http://localhost:$port/payment?paymentType={paymentType}&id={id}",
            Any::class.java,
            payload
        )

        val body = mapper.convertValue(response.body, CreditCardPayment::class.java)

        val expected = CreditCardPayment(
            status = PaymentStatusType.REJECTED,
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

        assertThat(response.statusCodeValue).isEqualTo(200)
        assertThat(body).isEqualTo(expected)
    }

    @Test
    fun `should return not found status code when bank slip payment not found`() {
        val payload = mapOf(
            "paymentType" to BANK_SLIP,
            "id" to 1L
        )

        val response = restTemplate.getForEntity(
            "http://localhost:$port/payment?paymentType={paymentType}&id={id}",
            Any::class.java,
            payload
        )

        assertThat(response.statusCodeValue).isEqualTo(404)
        assertThat(response.hasBody()).isFalse()
    }

    @Test
    fun `should return not found status code when credit card payment not found`() {
        val payload = mapOf(
            "paymentType" to CREDIT_CARD,
            "id" to 1L
        )

        val response = restTemplate.getForEntity(
            "http://localhost:$port/payment?paymentType={paymentType}&id={id}",
            Any::class.java,
            payload
        )

        assertThat(response.statusCodeValue).isEqualTo(404)
        assertThat(response.hasBody()).isFalse()
    }
}