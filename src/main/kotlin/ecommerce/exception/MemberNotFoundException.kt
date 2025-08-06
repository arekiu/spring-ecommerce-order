package ecommerce.exception

class MemberNotFoundException(message: String, cause: Throwable) :
    RuntimeException(message, cause)
