package ecommerce.repository

import ecommerce.exception.ProductAlreadyInDBException
import ecommerce.exception.ProductIdNotFoundException
import ecommerce.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull

fun ProductJpaRepository.getByIdOrThrow(id: Long): Product =
    findByIdOrNull(id)
        ?: throw ProductIdNotFoundException("Product not found")

fun ProductJpaRepository.existsByNameOrThrow(name: String) {
    if (existsByName(name)) throw ProductAlreadyInDBException("Product already exists with name: $name")
}

interface ProductJpaRepository : JpaRepository<Product, Long> {
    fun existsByName(name: String): Boolean
}
