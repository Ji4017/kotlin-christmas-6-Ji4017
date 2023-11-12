package christmas.controller

import christmas.validator.DateValidator
import christmas.validator.InputService
import christmas.validator.OrderValidator
import christmas.view.InputView

class PlannerController {
    fun run() {
        val visitDay = readVisitDay()
        val menus = readOrder()
    }

    private fun readVisitDay(): Int {
        val visitDay = InputService.inputWithRetry(
            prompt = { InputView.readVisitDay() },
            validator = { DateValidator(it) }
        )
        return visitDay.toInt()
    }

    private fun readOrder() {
        val order = InputService.inputWithRetry(
            prompt = { InputView.readOrder() },
            validator = { OrderValidator(it) }
        )
    }
}
