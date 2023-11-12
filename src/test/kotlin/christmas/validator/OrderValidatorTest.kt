package christmas.validator

import christmas.validator.OrderValidator.Companion.INVALID_ORDER_ERROR
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class OrderValidatorTest {
    @ParameterizedTest
    @ValueSource(strings = ["가나다", "가나다-1,", "가나다,1", "가나다-1-라마바-1", "가나다-1,가나다"])
    fun `메뉴 형식 검증`(value: String) {
        Assertions.assertThatIllegalArgumentException().isThrownBy {
            OrderValidator(value)
        }.withMessage(INVALID_ORDER_ERROR)
    }

    @Test
    fun `없는 메뉴 입력`() {
        Assertions.assertThatIllegalArgumentException().isThrownBy {
            OrderValidator("무지개돈까스-1")
        }.withMessage(INVALID_ORDER_ERROR)
    }

    @Test
    fun `메뉴 중복`() {
        Assertions.assertThatIllegalArgumentException().isThrownBy {
            OrderValidator("시저샐러드-1,시저샐러드-1")
        }.withMessage(INVALID_ORDER_ERROR)
    }


    @ParameterizedTest
    @ValueSource(strings = ["시저샐러드-하나", "시저샐러드-0"])
    fun `메뉴의 개수가 1이상의 숫자가 아닐 때`(value: String) {
        Assertions.assertThatIllegalArgumentException().isThrownBy {
            OrderValidator(value)
        }.withMessage(INVALID_ORDER_ERROR)
    }

    @Test
    fun `최대 주문 개수 초과`() {
        Assertions.assertThatIllegalArgumentException().isThrownBy {
            OrderValidator("시저샐러드-10, 초코케이크-11 ")
        }.withMessage(INVALID_ORDER_ERROR)
    }

    @ParameterizedTest
    @ValueSource(strings = ["해산물파스타-2", "시저샐러드-3,초코케이크-5,레드와인-3"])
    fun `올바른 값 입력`(value: String) {
        assertDoesNotThrow { OrderValidator(value) }
    }
}
