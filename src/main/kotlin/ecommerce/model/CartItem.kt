package ecommerce.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
data class CartItem(
    @ManyToOne
    @JoinColumn(name = "cart_id")
    var cart: Cart,
    @ManyToOne
    @JoinColumn(name = "product_id")
    val product: Product,
    var quantity: Int,
    @CreationTimestamp
    var createdAt: LocalDateTime = LocalDateTime.now(),
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
)
