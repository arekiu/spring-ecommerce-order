package ecommerce.service

import ecommerce.dto.ActiveUsersResponse
import ecommerce.dto.TopProductStats
import ecommerce.repository.CartHistoryJdbcRepository
import org.springframework.stereotype.Service

@Service
class AdminStatsService(
    private val cartHistoryJdbcRepository: CartHistoryJdbcRepository,
) {
    fun getTopProducts(): List<TopProductStats> {
        return cartHistoryJdbcRepository.getTopProducts()
    }

    fun getTopActiveUsers(): List<ActiveUsersResponse> {
        return cartHistoryJdbcRepository.getTop5ActiveUsers()
    }
}
