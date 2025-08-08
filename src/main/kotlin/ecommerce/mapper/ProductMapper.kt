package ecommerce.mapper

import ecommerce.dto.ProductRequest
import ecommerce.dto.ProductResponse
import ecommerce.model.Product

class ProductMapper

fun Product.toDto() = ProductResponse(id, name, price, imageUrl, options.map { it.toDto() })

fun ProductRequest.toEntity(): Product {
    return Product(
        name = name,
        price = price,
        imageUrl = imageUrl,
        options = options.map { it.toEntity() }.toMutableList(),
    )
}
