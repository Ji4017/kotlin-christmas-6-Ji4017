package christmas.domain

class Order(private val visitDay: Int, private val orderMenus: Map<String, Int>) {
    private var totalPrice = 0
    init {
        calculateTotalPrice()
    }

    private fun calculateTotalPrice() {
        for ((menuName, count) in orderMenus) {
            val menu = Menu.findByName(menuName)
            totalPrice += menu!!.price * count
        }
    }

    fun getOrderMenus(): Map<String, Int> {
        return orderMenus
    }

    fun getVisitDay(): Int {
        return visitDay
    }

    fun getTotalPrice(): Int {
        return totalPrice
    }
}
