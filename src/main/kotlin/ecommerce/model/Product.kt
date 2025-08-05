package ecommerce.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
data class Product(
    @Column(nullable = false, unique = true)
    val name: String,
    @OneToMany(mappedBy = "product", cascade = [CascadeType.PERSIST, CascadeType.REMOVE])
    val options: MutableList<Option> = mutableListOf(),
    @Column(nullable = false)
    val price: Double,
    val imageUrl: String,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
) {
    fun addOption(option: Option) {
        require(options.none { it.name == option.name })
        options.add(option)
    }

    init {
        require(options.isNotEmpty())
        require(options.distinctBy { it.name }.size == options.size)
    }
}
