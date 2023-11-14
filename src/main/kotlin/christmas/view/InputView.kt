package christmas.view

import camp.nextstep.edu.missionutils.Console

object InputView {
    private const val VISIT_DATE_PROMPT = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n" +
            "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"
    private const val MENU_PROMPT = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"
    private const val EVENT_AVAILABLE_PRICE_PROMPT = "총주문 금액 10,000원 이상부터 이벤트가 적용됩니다."

    fun readVisitDay(): String {
        println(VISIT_DATE_PROMPT)
        return Console.readLine().replace(" ", "").also { println(EVENT_AVAILABLE_PRICE_PROMPT) }
    }

    fun readOrder(): String {
        println(MENU_PROMPT)
        return Console.readLine().replace(" ", "")
    }
}
