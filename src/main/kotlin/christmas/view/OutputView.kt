package christmas.view

import christmas.domain.Menu
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

object OutputView {
    private const val EVENT_YEAR = 2023
    private const val EVENT_MONTH = 12
    private val STAR_DAYS = listOf(3, 10, 17, 24, 25, 31)
    private val DAY_OF_THE_WEEK = listOf("일", "월", "화", "수", "목", "금", "토")
    private const val MESSAGE_COUNT_SUFFIX = "개"
    private const val MESSAGE_ORDER_MENU = "<주문 메뉴>"
    private const val MESSAGE_TOTAL_PRICE = "<할인 전 총주문 금액>"
    private const val KRW = "원"
    private const val MESSAGE_GIFT = "<증정 메뉴>"
    private const val MESSAGE_BENEFIT_DETAILS = "<혜택 내역>"
    private const val MESSAGE_TOTAL_DISCOUNT = "<총혜택 금액>"
    private const val MESSAGE_FINAL_PRICE = "<할인 후 예상 결제 금액>"
    private const val MESSAGE_EVENT_BADGE = "<12월 이벤트 배지>"
    private const val NOTHING = "없음"
    private fun messageEventBenefitPreview(visitDay: Int) = "12월 ${visitDay}일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"

    fun printCalendar() {
        val date = LocalDate.of(EVENT_YEAR, EVENT_MONTH, 1)
        val daysInMonth = date.lengthOfMonth()
        val firstDayOfWeek = date.dayOfWeek.value % 7

        println("${date.year} ${date.month.getDisplayName(TextStyle.FULL, Locale.KOREAN)}")

        DAY_OF_THE_WEEK.forEach { print(String.format("%-3s", it)) }.also { println() }

        repeat((firstDayOfWeek % 7)) { print("    ") }
        for (day in 1..daysInMonth) {
            val dayString = if (day in STAR_DAYS) "*$day" else "$day"
            print(String.format("%-4s", dayString))
            if ((day + firstDayOfWeek) % 7 == 0) println()
        }
        println("\n")
    }

    fun printMenu() {
        val types = Menu.entries.map { it.type }.toSet()
        for (type in types) {
            println("<$type>")
            val menus = Menu.entries.filter { it.type == type }
            val menuDescriptions = menus.joinToString(", ") { "${it.name}(${String.format("%,d", it.price)})" }
            println(menuDescriptions)
            println()
        }
    }

    fun printBenefitPreviewMessage(visitDay: Int) {
        println(messageEventBenefitPreview(visitDay)).also { println() }
    }

    fun printOrderMenus(order: Map<String, Int>) {
        println(MESSAGE_ORDER_MENU)
        for ((menu, count) in order) {
            println("$menu $count$MESSAGE_COUNT_SUFFIX")
        }
        println()
    }

    fun printTotalPrice(totalPrice: Int) {
        val formattedNum = String.format("%,d", totalPrice) + KRW
        println(MESSAGE_TOTAL_PRICE)
        println(formattedNum).also { println() }
    }

    fun printGiftMenu(gift: String) {
        println(MESSAGE_GIFT)
        println(gift).also { println() }
    }

    fun printDiscountDetails(discounts: Map<String, Int>) {
        println(MESSAGE_BENEFIT_DETAILS)
        if (discounts.isEmpty()) {
            println(NOTHING).also { println() }
            return
        }

        for ((titleDiscount, discountPrice) in discounts) {
            println("$titleDiscount: ${String.format("-%,d", discountPrice)}"+KRW)
        }
        println()
    }

    fun printTotalDiscount(totalDiscount: Int) {
        val formattedNum = String.format("-%,d", totalDiscount) + KRW
        println(MESSAGE_TOTAL_DISCOUNT)
        println(formattedNum).also { println() }
    }

    fun printFinalPrice(finalPrice: Int) {
        val formattedNum = String.format("%,d", finalPrice) + KRW
        println(MESSAGE_FINAL_PRICE)
        println(formattedNum).also { println() }
    }

    fun printBadge(badge: String) {
        println(MESSAGE_EVENT_BADGE)
        print(badge)
    }
}
