package christmas.domain

import java.time.DayOfWeek
import java.time.LocalDate

class Event(private val orderInformation: Order) {
    private val orderMenus = orderInformation.getOrderMenus()
    private val visitDay = orderInformation.getVisitDay()
    private val totalPrice = orderInformation.getTotalPrice()
    private val visitDate = LocalDate.of(EVENT_YEAR, EVENT_MONTH, visitDay)
    private val discounts = mutableMapOf<String, Int>()
    private var gift = NOTHING

    init {
        if (totalPrice > EVENT_APPLIED_PRICE) {
            applyChristmasDiscount()
            applyWeekDayDiscount()
            applyWeekendDiscount()
            applySpecialDiscount()
            applyGiftDiscount()
        }
    }

    private fun applyChristmasDiscount() {
        var christmasDiscount = 0

        if (visitDay in CHRISTMAS_DISCOUNT_START..CHRISTMAS_DISCOUNT_END) {
            christmasDiscount += BASE_DISCOUNT + (visitDay - 1) * PER_DAY_DISCOUNT
        }
        discounts[TITLE_CHRISTMAS_DISCOUNT] = -christmasDiscount
    }

    private fun applyWeekDayDiscount() {
        var weekDayDiscount = 0

        for ((menuName, count) in orderMenus) {
            val menu = Menu.findByName(menuName)
            if (menu!!.type == "DESSERT" && visitDate.dayOfWeek !in listOf(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY)) {
                weekDayDiscount += WEEKDAY_DISCOUNT * count
            }
        }
        discounts[TITLE_WEEKDAY_DISCOUNT] = -weekDayDiscount
    }

    private fun applyWeekendDiscount() {
        var weekendDiscount = 0

        for ((menuName, count) in orderMenus) {
            val menu = Menu.findByName(menuName)
            if (menu!!.type == "MAIN" && visitDate.dayOfWeek in listOf(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY)) {
                weekendDiscount += WEEKEND_DISCOUNT * count
            }
        }
        discounts[TITLE_WEEKEND_DISCOUNT] = -weekendDiscount
    }

    private fun applySpecialDiscount() {
        if (visitDate.dayOfMonth in STAR_DAYS) {
            discounts[TITLE_SPECIAL_DISCOUNT] = -SPECIAL_DISCOUNT
        }
    }

    private fun applyGiftDiscount() {
        if (totalPrice >= GIFT_APPLIED_PRICE) {
            discounts[TITLE_GIFT_EVENT] = -GIFT_PRICE
            gift = GIFT_NAME
        }
    }

    fun getGift(): String {
        return gift
    }

    fun getDiscounts(): Map<String, Int> {
        return discounts
    }

    fun getTotalDiscount(): Int {
        return discounts.values.sum()
    }

    fun getBadge(): String {
        val totalDiscount = -(getTotalDiscount())

        return when {
            (totalDiscount >= BADGE_SANTA_PRICE) -> BADGE_SANTA
            (totalDiscount >= BADGE_TREE_PRICE) -> BADGE_TREE
            (totalDiscount >= BADGE_STAR_PRICE) -> BADGE_STAR
            else -> NOTHING
        }
    }

    companion object {
        const val EVENT_YEAR = 2023
        const val EVENT_MONTH = 12

        const val EVENT_APPLIED_PRICE = 10000
        const val GIFT_APPLIED_PRICE = 120000

        const val CHRISTMAS_DISCOUNT_START = 1
        const val CHRISTMAS_DISCOUNT_END = 25
        const val BASE_DISCOUNT = 1000
        const val PER_DAY_DISCOUNT = 100

        const val WEEKDAY_DISCOUNT = 2023
        const val WEEKEND_DISCOUNT = 2023

        val STAR_DAYS = listOf(3, 10, 17, 24, 25, 31)
        const val SPECIAL_DISCOUNT = 1000

        val GIFT_PRICE = Menu.샴페인.price

        const val TITLE_CHRISTMAS_DISCOUNT = "크리스마스 디데이 할인"
        const val TITLE_WEEKDAY_DISCOUNT = "평일 할인"
        const val TITLE_WEEKEND_DISCOUNT = "주말 할인"
        const val TITLE_SPECIAL_DISCOUNT = "특별 할인"
        const val TITLE_GIFT_EVENT = "증정 이벤트"
        const val GIFT_NAME = "샴페인 1개"
        const val NOTHING = "없음"

        const val BADGE_SANTA = "산타"
        const val BADGE_TREE = "트리"
        const val BADGE_STAR = "별"
        const val BADGE_SANTA_PRICE = 20000
        const val BADGE_TREE_PRICE = 10000
        const val BADGE_STAR_PRICE = 5000
    }
}
