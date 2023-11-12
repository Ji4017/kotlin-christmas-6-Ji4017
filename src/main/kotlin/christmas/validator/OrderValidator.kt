package christmas.validator

import christmas.domain.Menu

class OrderValidator(private val order: String) {
    private val menuNames: MutableList<String> = mutableListOf()
    private val menuCounts: MutableList<String> = mutableListOf()

    init {
        parseOrder()
        checkExistence()
        checkDuplication()
        checkCountIsDigit()
        checkMaximumCount()
    }

    private fun parseOrder() {
        order.split(",").forEach {
            val parts = it.split("-")
            require(parts.size == 2) { INVALID_ORDER_ERROR }
            val menuName = parts[0]
            val menuCount = parts[1]

            menuNames.add(menuName)
            menuCounts.add(menuCount)
        }
    }

    private fun checkExistence() {
        menuNames.forEach { menuName ->
            requireNotNull(Menu.findByName(menuName)) { INVALID_ORDER_ERROR }
        }
    }

    private fun checkDuplication() {
        val uniqueMenuNames = menuNames.toSet()
        require(uniqueMenuNames.size == menuNames.size) { INVALID_ORDER_ERROR }
    }

    private fun checkCountIsDigit() {
        require(menuCounts.all { it.toIntOrNull() != null && it.toInt() >= MINIMUM_ORDER_COUNT }) { INVALID_ORDER_ERROR }
    }

    private fun checkMaximumCount() {
        val totalOrderCount = menuCounts.sumOf { it.toInt() }
        require(totalOrderCount <= MAXIMUM_ORDER_COUNT) { INVALID_ORDER_ERROR }
    }

    companion object {
        const val MINIMUM_ORDER_COUNT = 1
        const val MAXIMUM_ORDER_COUNT = 20

        const val INVALID_ORDER_ERROR = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."
    }
}
