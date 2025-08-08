package ecommerce.mapper

import ecommerce.dto.OptionDto
import ecommerce.model.Option

class OptionMapper

fun Option.toDto(): OptionDto {
    return OptionDto(name, quantity)
}

fun OptionDto.toEntity(): Option {
    return Option(name, quantity)
}
