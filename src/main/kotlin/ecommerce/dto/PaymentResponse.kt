package ecommerce.dto

class PaymentResponse(
    val id: String,
    val amount: Int,
    val status: String,
    val clientSecret: String?,
    val errorMessage: String? = null,
)
