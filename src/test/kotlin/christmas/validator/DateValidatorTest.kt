package christmas.validator

import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DateValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = [" ", "", "ABC", "가나다", "###"])
    fun `숫자가 아닌 값 입력`(value: String) {
        assertThatIllegalArgumentException().isThrownBy {
            DateValidator(value)
        }.withMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.")
    }

    @ParameterizedTest
    @ValueSource(strings = ["0", "32"])
    fun `날짜 범위를 벗어난 값 입력`(value: String) {
        assertThatIllegalArgumentException().isThrownBy {
            DateValidator(value)
        }.withMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.")
    }

    @ParameterizedTest
    @ValueSource(strings = ["1", "01", "31"])
    fun `올바른 날짜 입력`(value: String) {
        assertDoesNotThrow {
            DateValidator(value)
        }
    }
}
