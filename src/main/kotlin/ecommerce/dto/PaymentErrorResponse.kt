package ecommerce.dto

data class PaymentErrorResponse(
    val code: String? = null,
    val declineCode: String? = null,
    val message: String? = null,
)
