package party.cattery.lulu.integration

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext

@SpringBootTest
class ApplicationIT : AbstractBaseIT() {

    @Test
    fun contextLoads(applicationContext: ApplicationContext) {
        assertNotNull(applicationContext)
    }
}
