package ecommerce.exception

class ProductCreationException(message: String, cause: Throwable) :
    RuntimeException(message, cause)
