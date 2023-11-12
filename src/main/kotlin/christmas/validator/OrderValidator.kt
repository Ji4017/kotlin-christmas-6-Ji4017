package christmas.validator

import christmas.domain.Menu

class OrderValidator(private val order: String) {
    private val parsedOrder: MutableMap<String, String> = mutableMapOf()

    init {
        checkForm()
        checkExistence()
//        checkDuplication()
//        checkMenuCount()
//        checkMenusCountRange()
    }

    private fun checkForm() {
        order.split(",").forEach {
            val parts = it.split("-")
            require(parts.size == 2) { INVALID_ORDER_ERROR }
            parsedOrder[parts[0]] = parts[1]
        }
    }

    private fun checkExistence() {
        parsedOrder.keys.forEach { key ->
            requireNotNull(Menu.findByName(key)) { INVALID_ORDER_ERROR }
        }
    }

    private fun checkDuplication() {

    }

    private fun checkMenuCount() {

    }

    private fun checkMenusCountRange() {

    }

    companion object {
        const val INVALID_ORDER_ERROR = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."
    }
}
