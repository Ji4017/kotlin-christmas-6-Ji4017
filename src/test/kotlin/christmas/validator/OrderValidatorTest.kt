package christmas.validator

import christmas.validator.OrderValidator.Companion.INVALID_ORDER_ERROR
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
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
}
