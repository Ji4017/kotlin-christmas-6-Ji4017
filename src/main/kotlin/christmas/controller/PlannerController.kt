package christmas.controller

import christmas.domain.Event
import christmas.domain.Order
import christmas.validator.DateValidator
import christmas.validator.InputService
import christmas.validator.OrderValidator
import christmas.view.InputView
import christmas.view.OutputView

class PlannerController {
    private var visitDay: Int = 0
    private lateinit var order: Order
    private lateinit var event: Event
    private var totalPrice: Int = 0

    fun run() {
        readVisitDay()
        val parsedOrder = readOrder()
        order = Order(parsedOrder)
        event = Event(parsedOrder, visitDay)
        printOrderDetails()
        printTotalPrice()
        printGift()

    }

    private fun readVisitDay() {
        visitDay = InputService.inputWithRetry(
            prompt = { InputView.readVisitDay() },
            validator = { DateValidator(it) }
        ).toInt()
    }

    private fun readOrder(): Map<String, Int> {
        val input = InputService.inputWithRetry(
            prompt = { InputView.readOrder() },
            validator = { OrderValidator(it) }
        )
        return  InputService.parseOrder(input)
//        order = Order(parsedOrder)
    }

    private fun printOrderDetails() {
        OutputView.benefitPreview()
        OutputView.orderDetails(order.getOrder())
    }

    private fun printTotalPrice() {
        totalPrice = order.totalPrice()
        OutputView.totalPrice(totalPrice)
    }

    private fun printGift() {
        OutputView.gift(event.gift(totalPrice))
    }
}
