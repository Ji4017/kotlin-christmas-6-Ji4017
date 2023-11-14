package christmas.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class OrderTest {
    @Test
    fun `총주문 금액 테스트`() {
        val order = Order(5, mapOf("양송이수프" to 2, "타파스" to 1))

        // 예상되는 총 가격은 17500 (2*6000 + 1*5500)
        val expected = 17500
        val result = order.getTotalPrice()

        assertEquals(expected, result)
    }
}
