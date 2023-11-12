package christmas.validator

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class InputServiceTest {
    @Test
    fun `문자열을_맵으로_정확히_변환`() {
        val input = "해산물파스타-2,레드와인-3"
        val expected = mapOf("해산물파스타" to 2, "레드와인" to 3)

        val result = InputService.parseOrder(input)

        assertEquals(expected, result)
    }
}
