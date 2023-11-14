package christmas.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class EventTest {

    @Test
    fun `크리스마스 할인 적용 테스트`() {
        val visitDay = 6
        val orderInformation = Order(mapOf("양송이수프" to 2, "타파스" to 1), visitDay)
        val benefit = Event(orderInformation)
        val discounts = benefit.getDiscounts()
        assertEquals(-(Event.BASE_DISCOUNT + (visitDay-1) * Event.PER_DAY_DISCOUNT), discounts[Event.TITLE_CHRISTMAS_DISCOUNT])
    }

    @Test
    fun `평일 할인 적용 테스트`() {
        val dessertCount = 3
        val orderInformation = Order(mapOf("초코케이크" to dessertCount, "양송이수프" to 2), 6)
        val benefit = Event(orderInformation)
        val discounts = benefit.getDiscounts()
        assertEquals(-(Event.WEEKDAY_DISCOUNT * dessertCount), discounts[Event.TITLE_WEEKDAY_DISCOUNT])
    }

    @Test
    fun `주말 할인 적용 테스트`() {
        val mainCount = 2
        val saturDay = 9
        val orderInformation = Order(mapOf("티본스테이크" to mainCount, "양송이수프" to 2), saturDay)
        val benefit = Event(orderInformation)
        val discounts = benefit.getDiscounts()
        assertEquals(-(Event.WEEKEND_DISCOUNT * mainCount), discounts[Event.TITLE_WEEKEND_DISCOUNT])
    }

    @Test
    fun `특별 할인 적용 테스트`() {
        val starDay = 3
        val orderInformation = Order(mapOf("양송이수프" to 2, "타파스" to 1), starDay)
        val benefit = Event(orderInformation)
        val discounts = benefit.getDiscounts()
        assertEquals(-(Event.SPECIAL_DISCOUNT), discounts[Event.TITLE_SPECIAL_DISCOUNT])
    }

    @Test
    fun `증정 이벤트 적용 테스트`() {
        val orderInformation = Order(mapOf("해산물파스타" to 2, "레드와인" to 1, "초코케이크" to 1), 6)
        val benefit = Event(orderInformation)
        val discounts = benefit.getDiscounts()
        assertEquals(-(Event.GIFT_PRICE), discounts[Event.TITLE_GIFT_EVENT])
    }

    @Test
    fun `증정품 경곗값 테스트`() {
        // 주문의 총 가격이 선물이 증정되는 120,000원 이상으로 되도록 (양송이수프=6000, 레드와인=60000)
        val orderWithGift = Order(mapOf("양송이수프" to 10, "레드와인" to 1), 6)
        val given = Event(orderWithGift)

        // 주문의 총 가격이 선물 증정 가격 이하
        val orderWithoutGift = Order(mapOf("양송이수프" to 2, "타파스" to 1), 6)
        val nothing = Event(orderWithoutGift)

        assertEquals(Event.GIFT_NAME, given.getGift())
        assertEquals(Event.NOTHING, nothing.getGift())
    }
}
