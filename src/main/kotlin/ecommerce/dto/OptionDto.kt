package ecommerce.dto

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

class OptionDto(
    @field:NotBlank(message = "Option name must not be blank")
    @field:Size(max = 50, min = 1, message = "Maximal 50 characters")
    @field:Pattern(
        regexp = "^[\\w\\s()\\[\\]+\\-&/_]*$",
        message = "Invalid characters in name. Allowed: letters, numbers, spaces, (, ), [, ], +, -, &, /, _",
    )
    val name: String,
    @field:Min(1)
    @field:Max(100_000_000)
    val quantity: Int,
)
