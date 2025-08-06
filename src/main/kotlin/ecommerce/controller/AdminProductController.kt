package ecommerce.controller

import ecommerce.annotation.AdminOnly
import ecommerce.dto.MemberDto
import ecommerce.dto.OptionDto
import ecommerce.dto.ProductPatchRequest
import ecommerce.dto.ProductRequest
import ecommerce.dto.ProductResponse
import ecommerce.service.ProductService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/admin/products")
class AdminProductController(
    private val productService: ProductService,
) {
    @PostMapping
    fun createProduct(
        @Valid @RequestBody product: ProductRequest,
        @AdminOnly member: MemberDto,
    ): ResponseEntity<ProductResponse> {
        val newProduct = productService.createProduct(product)
        return ResponseEntity.ok(newProduct)
    }

    @GetMapping
    fun getAllProducts(
        @AdminOnly member: MemberDto,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "5") size: Int,
        @RequestParam(defaultValue = "id") sortBy: String,
        @RequestParam(defaultValue = "true") ascending: Boolean,
    ): ResponseEntity<Page<ProductResponse>> {
        return ResponseEntity.ok(productService.findAll(page, size, sortBy, ascending))
    }

    @GetMapping("/{id}")
    fun getProduct(
        @PathVariable id: Long,
        @AdminOnly member: MemberDto,
    ): ResponseEntity<ProductResponse> {
        return ResponseEntity.ok(productService.findById(id))
    }

    @PatchMapping("/{id}")
    fun updateProduct(
        @PathVariable id: Long,
        @Valid @RequestBody product: ProductPatchRequest,
        @AdminOnly member: MemberDto,
    ): ResponseEntity<ProductResponse> {
        val updatedProduct = productService.updateProduct(id, product)
        return ResponseEntity.ok(updatedProduct)
    }

    @DeleteMapping("/{id}")
    fun deleteProduct(
        @PathVariable id: Long,
        @AdminOnly member: MemberDto,
    ): ResponseEntity<Void> {
        productService.deleteProduct(id)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/add/option/{id}")
    fun addOptionToProduct(
        @PathVariable id: Long,
        @AdminOnly member: MemberDto,
        @RequestBody option: OptionDto,
    ): ResponseEntity<ProductResponse> {
        val updatedProduct = productService.addOptionToProduct(id, option)
        return ResponseEntity.ok(updatedProduct)
    }
}
