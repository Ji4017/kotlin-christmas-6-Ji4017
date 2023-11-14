package christmas.validator

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class OrderValidatorTest {

    private fun assertInvalidOrder(value: String, message: String) {
        Assertions.assertThatIllegalArgumentException().isThrownBy {
            OrderValidator(value)
        }.withMessage(message)
    }

    @ParameterizedTest
    @ValueSource(strings = ["가나다", "가나다-1,", "가나다,1", "가나다-1-라마바-1", "가나다-1,가나다"])
    fun `메뉴 형식 검증`(value: String) {
        assertInvalidOrder(value, OrderValidator.INVALID_ORDER_ERROR)
    }

    @Test
    fun `없는 메뉴 입력`() {
        assertInvalidOrder("무지개돈까스-1", OrderValidator.INVALID_ORDER_ERROR)
    }

    @Test
    fun `메뉴 중복`() {
        assertInvalidOrder("시저샐러드-1,시저샐러드-1", OrderValidator.INVALID_ORDER_ERROR)
    }

    @Test
    fun `음료만 주문`() {
        assertInvalidOrder("레드와인-2", OrderValidator.INVALID_ORDER_ERROR)
    }

    @ParameterizedTest
    @ValueSource(strings = ["시저샐러드-하나", "시저샐러드-0"])
    fun `메뉴의 개수가 1이상의 숫자가 아닐 때`(value: String) {
        assertInvalidOrder(value, OrderValidator.INVALID_ORDER_ERROR)
    }

    @Test
    fun `최대 주문 개수 초과`() {
        assertInvalidOrder("시저샐러드-10,초코케이크-11", OrderValidator.MAXIMUM_COUNT_ERROR)
    }

    @ParameterizedTest
    @ValueSource(strings = ["해산물파스타-2", "시저샐러드-3,초코케이크-5,레드와인-3"])
    fun `올바른 값 입력`(value: String) {
        assertDoesNotThrow { OrderValidator(value) }
    }
}
