package christmas.view

import camp.nextstep.edu.missionutils.Console
import christmas.constant.Constants.MAX_ATTEMPTS

class InputView {
    companion object {
        private const val VISIT_DATE_PROMPT = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n" +
                "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"
        private const val MENU_PROMPT = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"

        fun readVisitDay(): String {
            println(VISIT_DATE_PROMPT)
            return Console.readLine().replace(" ", "")
        }

        fun readMenu() {
            println(MENU_PROMPT)
        }

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
