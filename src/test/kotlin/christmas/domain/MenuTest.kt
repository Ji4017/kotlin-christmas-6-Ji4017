package christmas.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MenuTest {
    @Test
    fun `메뉴 반환 테스트`() {
        val menuName = "양송이수프"
        val menu = Menu.findByName(menuName)
        assertNotNull(menu)
        assertEquals(menuName, menu!!.name)
    }
}
