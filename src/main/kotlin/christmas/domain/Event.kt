package christmas.domain

class Event(private val order: Map<String, Int>, private val visitDay: Int) {

    init {
        christmas()
        weekDay()
        weekend()
        special()
    }

    private fun christmas() {

    }

    private fun weekDay() {

    }

    private fun weekend() {

    }

    private fun special() {

    }

    fun gift(totalPrice: Int): String {
        if (totalPrice >= GIFT_EVENT_AMOUNT) {
            return "샴페인 1개"
        }
        return "없음"
    }

    fun profitDetail() {

    }

    fun totalDiscount() {

    }

    fun badge() {

    }

    companion object {
        const val GIFT_EVENT_AMOUNT = 120000
    }
}
