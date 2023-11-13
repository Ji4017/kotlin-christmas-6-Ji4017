package christmas.domain

import christmas.domain.Event.Companion.GIFT_EVENT_AMOUNT
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class EventTest {
    val event = Event(emptyMap(), 0)
    @Test
    fun `증정품 경곗값 테스트`() {
        val result1 = event.gift(GIFT_EVENT_AMOUNT)
        val result2 = event.gift(GIFT_EVENT_AMOUNT - 1)

        assertEquals("샴페인 1개", result1)
        assertEquals("없음", result2)
    }
}
