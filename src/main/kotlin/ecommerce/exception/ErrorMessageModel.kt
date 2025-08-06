package ecommerce.exception

data class ErrorMessageModel(
    val status: Int,
    val message: String?,
    val causeMessage: String? = null,
)
