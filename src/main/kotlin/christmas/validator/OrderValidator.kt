package christmas.validator

class OrderValidator(private val order: String) {
    init {
        checkForm()
//        checkExistence()
//        checkDuplication()
//        checkMenuCount()
//        checkMenusCountRange()
    }

    private fun checkForm() {
        order.split(",").forEach {
            val parts = it.split("-")
            require(parts.size==2) {  "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요." }
        }
    }

//    private fun checkExistence() {
//
//    }

//    private fun checkDuplication() {
//
//    }
//
//    private fun checkMenuCount() {
//
//    }
//
//    private fun checkMenusCountRange() {
//
//    }
}
