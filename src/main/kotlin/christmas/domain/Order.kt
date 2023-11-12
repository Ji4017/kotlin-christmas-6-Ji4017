package christmas.domain

class Order(private val order: Map<String, Int>) {

    fun getOrder(): Map<String, Int> {
        return order
    }

    fun totalPrice() {

    }
}
