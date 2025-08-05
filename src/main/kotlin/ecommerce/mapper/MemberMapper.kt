package ecommerce.mapper

import ecommerce.dto.MemberDto
import ecommerce.model.Member

class MemberMapper

fun Member.toDto() = MemberDto(id, email, role)
