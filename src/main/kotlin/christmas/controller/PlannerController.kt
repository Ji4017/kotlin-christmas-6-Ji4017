package christmas.controller

import christmas.domain.Order
import christmas.validator.DateValidator
import christmas.validator.InputService
import christmas.validator.OrderValidator
import christmas.view.InputView
import christmas.view.OutputView

class PlannerController {
    private var visitDay: Int? = null
    private lateinit var order: Order

    fun run() {
        readVisitDay()
        readOrder()
        printOrderDetails()
    }

    private fun readVisitDay() {
        visitDay = InputService.inputWithRetry(
            prompt = { InputView.readVisitDay() },
            validator = { DateValidator(it) }
        ).toInt()
    }

    private fun readOrder() {
        val input = InputService.inputWithRetry(
            prompt = { InputView.readOrder() },
            validator = { OrderValidator(it) }
        )
        val parsedOrder =  InputService.parseOrder(input)
        order = Order(parsedOrder)
    }

    private fun printOrderDetails() {
        OutputView.benefitPreview()
        OutputView.orderDetails(order.getOrder())
    }
}
