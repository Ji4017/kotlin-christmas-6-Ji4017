package christmas.controller

import christmas.domain.Event
import christmas.domain.Order
import christmas.file.EventStatisticsWriter
import christmas.validator.DateValidator
import christmas.validator.InputService
import christmas.validator.OrderValidator
import christmas.view.InputView
import christmas.view.OutputView

class PlannerController {

    fun run() {
        val visitDay = readVisitDay()
        val orderMenus = readOrder()

        val orderInformation = Order(orderMenus, visitDay)
        val benefit = Event(orderInformation)

        printOrderMenus(orderMenus)
        printTotalPrice(orderInformation.getTotalPrice())
        printGift(benefit.getGift())
        printDiscountDetails(benefit.getDiscounts())
        printTotalDiscount(benefit.getTotalDiscount())
        val finalPrice = printFinalPrice(orderInformation.getTotalPrice(), benefit.getTotalDiscount())
        printBadge(benefit.getBadge())
        saveEventStatistics(finalPrice)
    }

    private fun readVisitDay(): Int {
        val input = InputService.inputWithRetry(
            prompt = { InputView.readVisitDay() },
            validator = { DateValidator(it) }
        ).toInt()
        return input
    }

    private fun readOrder(): Map<String, Int> {
        val input = InputService.inputWithRetry(
            prompt = { InputView.readOrder() },
            validator = { OrderValidator(it) }
        )
        return InputService.parseOrder(input)
    }

    private fun printOrderMenus(orderMenus: Map<String, Int>) {
        OutputView.printBenefitPreview()
        OutputView.printOrderMenus(orderMenus)
    }

    private fun printTotalPrice(totalPrice: Int) {
        OutputView.printTotalPrice(totalPrice)
    }

    private fun printGift(gift: String) {
        OutputView.printGiftMenu(gift)
    }

    private fun printDiscountDetails(discounts: Map<String, Int>) {
        val filteredDiscounts = discounts.filterValues { discountPrice ->
            discountPrice != 0
        }
        OutputView.printDiscountDetails(filteredDiscounts)
    }

    private fun printTotalDiscount(totalDiscount: Int) {
        OutputView.printTotalDiscount(totalDiscount)
    }

    private fun printFinalPrice(totalPrice: Int, totalDiscount: Int): Int {
        val finalPrice = totalPrice + totalDiscount
        OutputView.printFinalPrice(finalPrice)
        return finalPrice
    }

    private fun printBadge(badge: String) {
        OutputView.printBadge(badge)
    }

    private fun saveEventStatistics(salesAmount: Int) {
        val writer = EventStatisticsWriter()
        writer.writeSalesAmount(salesAmount)
        writer.writeNumberOfParticipants()
    }
}
