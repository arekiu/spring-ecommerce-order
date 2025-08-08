package ecommerce.model

import ecommerce.exception.InsufficientQuantityException
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class Option(
    val name: String,
    var quantity: Int,
    @ManyToOne
    @JoinColumn(name = "product_id")
    var product: Product? = null,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
) {
    fun reduceOptionQuantity(value: Int) {
        if (quantity < value) {
            throw InsufficientQuantityException("Insufficient quantity: $quantity")
        }
        quantity -= value
    }
}
