package christmas.validator

class InputService {
    companion object {
        const val MAX_ATTEMPTS = 10

        fun <T> inputWithRetry(prompt: () -> T, validator: (value: T) -> Unit): T {
            repeat(MAX_ATTEMPTS) {
                try {
                    val input = prompt()
                    validator(input)
                    return input
                } catch (e: IllegalArgumentException) {
                    println(e.message)
                }
            }
            throw IllegalArgumentException("입력에 여러 차례 실패했습니다. 프로그램을 종료합니다.")
        }

        fun parseOrder(input: String): Map<String, Int> {
            return input.split(",").associate {
                val (menu, count) = it.split("-")
                menu to count.toInt()
            }
        }
    }
}
