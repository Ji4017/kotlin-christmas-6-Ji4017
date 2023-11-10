package christmas.controller

import christmas.validator.DateValidator
import christmas.validator.InputService
import christmas.view.InputView

class PlannerController {
    fun run() {
        val visitDay = readVisitDay()
    }

    private fun readVisitDay(): Int {
        val visitDay = InputService.inputWithRetry(
            prompt = { InputView.readVisitDay() },
            validator = { DateValidator(it) }
        )
        return visitDay.toInt()
    }
}
