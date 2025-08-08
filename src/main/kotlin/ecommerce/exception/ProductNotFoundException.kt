package ecommerce.exception

class ProductNotFoundException(message: String, cause: Throwable) : RuntimeException(message, cause)
