package ecommerce.controller

import ecommerce.annotation.LoginMember
import ecommerce.dto.MemberDto
import ecommerce.dto.OrderResponse
import ecommerce.dto.PaymentRequest
import ecommerce.mapper.toResponse
import ecommerce.service.OrderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/orders")
class OrderController(
    private val orderService: OrderService,
) {
    @PostMapping("/place")
    fun placeOrder(
        @LoginMember member: MemberDto,
        @RequestBody paymentRequest: PaymentRequest,
    ): ResponseEntity<OrderResponse> {
        val order = orderService.placeOrder(member.id, paymentRequest)
        return ResponseEntity.ok(order.toResponse())
    }
}
