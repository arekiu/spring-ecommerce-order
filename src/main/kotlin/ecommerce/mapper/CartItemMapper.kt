package ecommerce.mapper

import ecommerce.dto.CartItemResponse
import ecommerce.model.CartItem

class CartItemMapper

fun CartItem.toDto(): CartItemResponse {
    return CartItemResponse(
        product.id,
        productName = product.name,
        quantity = quantity,
        productPrice = product.price,
        productImageUrl = product.imageUrl,
    )
}
