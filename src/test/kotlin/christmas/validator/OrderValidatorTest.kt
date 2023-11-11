package christmas.validator

import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class OrderValidatorTest {
    @ParameterizedTest
    @ValueSource(strings = ["가나다", "가나다-1,", "가나다,1", "가나다-1-라마바-1", "가나다-1,가나다"])
    fun `메뉴 형식 검증`(value: String) {
        Assertions.assertThatIllegalArgumentException().isThrownBy {
            OrderValidator(value)
        }.withMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.")
    }
}
