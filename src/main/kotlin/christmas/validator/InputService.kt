package christmas.validator

import christmas.constant.Constants.MAX_ATTEMPTS

class InputService {
    companion object {
        fun <T> inputWithRetry(prompt: () -> T, validator: (value: T) -> Unit): T {
            repeat(MAX_ATTEMPTS) {
                try {
                    val value = prompt()
                    validator(value)
                    return value
                } catch (e: IllegalArgumentException) {
                    println(e.message)
                }
            }
            throw IllegalArgumentException("입력에 여러 차례 실패했습니다. 프로그램을 종료합니다.")
        }
    }
}
