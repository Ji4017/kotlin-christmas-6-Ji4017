package christmas.view

object OutputView {
    private const val MESSAGE_EVENT_BENEFIT_PREVIEW = "12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"
    private const val MESSAGE_COUNT_SUFFIX = "개"
    private const val MESSAGE_ORDER_MENU = "<주문 메뉴>"
    private const val MESSAGE_TOTAL_PRICE = "<할인 전 총주문 금액>"
    private const val MESSAGE_GIFT = "<증정 메뉴>"

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

    fun totalPrice(totalPrice: Int) {
        println(MESSAGE_TOTAL_PRICE)
        val formattedNum = String.format("%,d", totalPrice)
        println(formattedNum)
        println()
    }

    fun gift(gift: String) {
        println(MESSAGE_GIFT)
        println(gift)
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
