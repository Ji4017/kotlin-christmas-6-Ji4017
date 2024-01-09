package christmas.validator

import christmas.domain.Menu

class OrderValidator(private val order: String) {
    private val menuNames: MutableList<String> = mutableListOf()
    private val menuCounts: MutableList<String> = mutableListOf()

    init {
        parseOrder()
        checkExistence()
        checkDuplication()
        checkOnlyDrink()
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

    private fun checkOnlyDrink() {
        var isOnlyDrink = true

        for (menuName in menuNames) {
            val menu = Menu.findByName(menuName)
            if (menu!!.type != "DRINK") {
                isOnlyDrink = false
                break
            }
        }
        require(isOnlyDrink == false) { ONLY_DRINK_ERROR }
    }

    private fun checkCountIsDigit() {
        require(menuCounts.all { it.toIntOrNull() != null && it.toInt() >= MINIMUM_ORDER_COUNT }) { INVALID_ORDER_ERROR }
    }

    private fun checkMaximumCount() {
        val totalOrderCount = menuCounts.sumOf { it.toInt() }
        require(totalOrderCount <= MAXIMUM_ORDER_COUNT) { MAXIMUM_COUNT_ERROR }
    }

    companion object {
        const val MINIMUM_ORDER_COUNT = 1
        const val MAXIMUM_ORDER_COUNT = 20

        const val INVALID_ORDER_ERROR = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."
        const val MAXIMUM_COUNT_ERROR = "[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다."
        const val ONLY_DRINK_ERROR = "[ERROR] 음료만 주문 시, 주문할 수 없습니다."
    }
}
