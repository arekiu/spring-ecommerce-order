package ecommerce.repository

import ecommerce.exception.ElementNotFoundException
import ecommerce.model.Cart
import org.springframework.data.jpa.repository.JpaRepository

fun CartJpaRepository.getByMemberId(memberId: Long): Cart =
    findByMemberId(memberId)
        ?: throw ElementNotFoundException("No cart found")

interface CartJpaRepository : JpaRepository<Cart, Long> {
    fun findByMemberId(memberId: Long): Cart?
}
