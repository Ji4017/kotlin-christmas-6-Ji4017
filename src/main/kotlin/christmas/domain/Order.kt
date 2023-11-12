package christmas.domain

class Order(private val order: Map<String, Int>) {

    fun getOrder(): Map<String, Int> {
        return order
    }

    fun totalPrice(): Int {
        var total = 0
        for ((menuName, count) in order) {
            val menu = Menu.findByName(menuName)
            total += menu!!.price * count
        }
        return total
    }
}
