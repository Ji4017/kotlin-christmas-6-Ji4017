package christmas.view

import christmas.domain.Order

object OutputView {
    private const val MESSAGE_EVENT_BENEFIT_PREVIEW = "12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"
    private const val MESSAGE_COUNT_SUFFIX = "개"
    private const val MESSAGE_ORDER_MENU = "<주문 메뉴>"

    fun benefitPreview() {
        println(MESSAGE_EVENT_BENEFIT_PREVIEW)
        println()
    }

    fun orderDetails(order: Map<String, Int>) {
        println(MESSAGE_ORDER_MENU)
        for ((menu, count) in order) {
            println("$menu $count$MESSAGE_COUNT_SUFFIX")
        }
        println()
    }

    fun totalPrice() {

    }

    fun gift() {

    }

    fun discountDetails() {

    }

    fun totalDiscount() {

    }

    fun amountOfPayment() {

    }

    fun badge() {

    }
}
