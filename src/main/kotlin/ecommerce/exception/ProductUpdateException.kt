package ecommerce.exception

class ProductUpdateException(message: String, cause: Throwable) :
    RuntimeException(message, cause)
