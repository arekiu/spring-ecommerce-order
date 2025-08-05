package ecommerce.repository

import ecommerce.model.Option
import ecommerce.model.Product
import org.assertj.core.api.Assertions.assertThat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import kotlin.test.Test

@DataJpaTest
class ProductRepositoryTest {
    @Autowired
    private lateinit var productJpaRepository: ProductJpaRepository

    @Test
    fun save() {
        val option = Option("test", 10)
        val expected = Product("test", mutableListOf(option), 10.0, "https://google.com")
        val actual = productJpaRepository.save(expected)
        assertThat(actual.id).isNotNull
        assertThat(actual.id).isNotZero
        assertThat(actual.name).isEqualTo(expected.name)
        assertThat(actual.options.first().name).isEqualTo("test")
        assertThat(actual.options.first().quantity).isEqualTo(10)

    }

    @Test
    fun findAll() {
        val option = Option("test", 10)
        val expected = Product("test", mutableListOf(option), 10.0, "https://google.com")
        val expected2 = Product("test2", mutableListOf(option), 10.0, "https://google.com")
        productJpaRepository.save(expected)
        productJpaRepository.save(expected2)
        val products = productJpaRepository.findAll()
        assertThat(products).hasSize(12)
    }

    @Test
    fun findFyId() {
        val product = productJpaRepository.findById(1).get()
        assertThat(product.name).isEqualTo("Espresso")
    }

    @Test
    fun existByName() {
        val option = Option("test", 10)
        val expected = Product("test", mutableListOf(option), 10.0, "https://google.com")
        productJpaRepository.save(expected)
        val product = productJpaRepository.existsByName("test")
        assertThat(product).isTrue
    }
}
