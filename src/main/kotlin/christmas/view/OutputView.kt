package christmas.view

object OutputView {
    private const val MESSAGE_EVENT_BENEFIT_PREVIEW = "12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"
    private const val MESSAGE_COUNT_SUFFIX = "개"
    private const val MESSAGE_ORDER_MENU = "<주문 메뉴>"
    private const val MESSAGE_TOTAL_PRICE = "<할인 전 총주문 금액>"
    private const val MESSAGE_GIFT = "<증정 메뉴>"
    private const val MESSAGE_BENEFIT_DETAILS = "<혜택 내역>"
    private const val MESSAGE_TOTAL_DISCOUNT = "<총혜택 금액>"
    private const val MESSAGE_FINAL_PRICE = "<할인 후 예상 결제 금액>"
    private const val MESSAGE_EVENT_BADGE = "<12월 이벤트 배지>"

    fun printBenefitPreview() {
        println(MESSAGE_EVENT_BENEFIT_PREVIEW).also { println() }
    }

    fun printOrderMenus(order: Map<String, Int>) {
        println(MESSAGE_ORDER_MENU)
        for ((menu, count) in order) {
            println("$menu $count$MESSAGE_COUNT_SUFFIX")
        }
        println()
    }

    fun printTotalPrice(totalPrice: Int) {
        val formattedNum = String.format("%,d", totalPrice) + "원"
        println(MESSAGE_TOTAL_PRICE)
        println(formattedNum).also { println() }
    }

    fun printGiftMenu(gift: String) {
        println(MESSAGE_GIFT)
        println(gift).also { println() }
    }

    fun printDiscountDetails(discounts: Map<String, Int>) {
        println(MESSAGE_BENEFIT_DETAILS)
        for ((titleDiscount, discountPrice) in discounts) {
            println("$titleDiscount: ${String.format("%,d", discountPrice)}원")
        }
        println()
    }

    fun printTotalDiscount(totalDiscount: Int) {
        val formattedNum = String.format("%,d", totalDiscount) + "원"
        println(MESSAGE_TOTAL_DISCOUNT)
        println(formattedNum).also { println() }
    }

    fun printFinalPrice(finalPrice: Int) {
        val formattedNum = String.format("%,d", finalPrice) + "원"
        println(MESSAGE_FINAL_PRICE)
        println(formattedNum).also { println() }
    }

    fun printBadge(badge: String) {
        println(MESSAGE_EVENT_BADGE)
        print(badge)
    }
}
