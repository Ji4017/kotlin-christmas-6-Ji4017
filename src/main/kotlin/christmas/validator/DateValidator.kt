package christmas.validator

import christmas.constant.Constants.EVENT_MONTH
import java.time.LocalDate

class DateValidator(private val visitDay: String) {
    init {
        checkDigit()
        checkDayRange()
        checkCurrentDate()
    }

    private fun checkDigit() {
        requireNotNull(visitDay.toIntOrNull()) { "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요." }
    }

    private fun checkDayRange() {
        require(visitDay.toInt() in 1..31) { "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요." }
    }

    private fun checkCurrentDate() {
        val currentDate = LocalDate.now()
        val currentMonth = currentDate.monthValue
        val currentDay = currentDate.dayOfMonth

        if (currentMonth == EVENT_MONTH) {
            println("if문 실행")
            require(currentDay < visitDay.toInt()) { "[ERROR] 현재 날짜보다 이전의 날짜는 선택할 수 없습니다." }
        }
    }
}
