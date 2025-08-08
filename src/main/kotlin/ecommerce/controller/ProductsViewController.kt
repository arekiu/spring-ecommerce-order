package ecommerce.controller

import ecommerce.repository.ProductJpaRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/products")
class ProductsViewController(
    private val productJpaRepository: ProductJpaRepository,
) {
    @GetMapping
    fun getProducts(model: Model): String {
        model.addAttribute("products", productJpaRepository.findAll())
        return "index"
    }
}
