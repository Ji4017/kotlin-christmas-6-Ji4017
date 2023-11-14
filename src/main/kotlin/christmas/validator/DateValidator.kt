package christmas.validator

import java.time.LocalDate

class DateValidator(private val visitDay: String) {
    init {
        checkDigit()
        checkDayRange()
        checkCurrentDate()
    }

    private fun checkDigit() {
        requireNotNull(visitDay.toIntOrNull()) { INVALID_DATE_FORM_ERROR }
    }

    private fun checkDayRange() {
        require(visitDay.toInt() in 1..31) { INVALID_DATE_FORM_ERROR }
    }

    private fun checkCurrentDate() {
        val currentDate = LocalDate.now()
        val currentMonth = currentDate.monthValue
        val currentDay = currentDate.dayOfMonth

        if (currentMonth == EVENT_MONTH) {
            require(currentDay < visitDay.toInt()) { INVALID_DAY_ERROR }
        }
    }

    companion object {
        private const val EVENT_MONTH = 12
        private const val INVALID_DATE_FORM_ERROR = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."
        private const val INVALID_DAY_ERROR = "[ERROR] 현재 날짜보다 이전의 날짜는 선택할 수 없습니다."
    }
}
