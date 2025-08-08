package ecommerce.repository

import org.assertj.core.api.Assertions.assertThat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import kotlin.test.Test

@DataJpaTest
class CartRepositoryTest {
    @Autowired
    private lateinit var cartRepository: CartJpaRepository

    @Test
    fun findCartByMemberId() {
        val memberId = 1L
        val cart = cartRepository.getByMemberId(memberId)

        assertThat(cart.member.id).isEqualTo(memberId)
    }
}
