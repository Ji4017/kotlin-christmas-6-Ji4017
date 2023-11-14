package christmas.controller

import christmas.domain.Event
import christmas.domain.Order
import christmas.file.EventStatisticsWriter
import christmas.service.InputService
import christmas.validator.DateValidator
import christmas.validator.OrderValidator
import christmas.view.InputView
import christmas.view.OutputView

class PlannerController {

    fun run() {
        val (visitDay, orderMenus) = readUserInput()

        val orderInformation = Order(visitDay, orderMenus)
        val benefit = Event(orderInformation)

        val finalPrice = ExcuteEventPlanner(orderInformation, benefit)

        saveEventStatistics(finalPrice)
    }

    private fun readUserInput(): Pair<Int, Map<String, Int>> {
        val visitDay = InputService.inputWithRetry(
            prompt = { InputView.readVisitDay() },
            validator = { DateValidator(it) }
        ).toInt()

        val order = InputService.inputWithRetry(
            prompt = { InputView.readOrder() },
            validator = { OrderValidator(it) }
        )
        return Pair(visitDay, InputService.parseOrder(order))
    }

    private fun ExcuteEventPlanner(orderInformation: Order, benefit: Event): Int {
        val totalPrice = orderInformation.getTotalPrice()
        val totalDiscount = benefit.getTotalDiscount()
        val finalPrice = totalPrice - totalDiscount

        OutputView.printBenefitPreviewMessage()
        OutputView.printOrderMenus(orderInformation.getOrderMenus())
        OutputView.printTotalPrice(totalPrice)
        OutputView.printGiftMenu(benefit.getGift())
        OutputView.printDiscountDetails(benefit.getDiscounts())
        OutputView.printTotalDiscount(totalDiscount)
        OutputView.printFinalPrice(finalPrice)
        OutputView.printBadge(benefit.getBadge())
        return finalPrice
    }

    private fun saveEventStatistics(salesAmount: Int) {
        val writer = EventStatisticsWriter()
        writer.writeSalesAmount(salesAmount)
        writer.writeNumberOfParticipants()
    }
}
