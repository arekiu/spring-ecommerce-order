package ecommerce.repository

import ecommerce.dto.ActiveUsersResponse
import ecommerce.dto.TopProductStats
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class CartHistoryJdbcRepository(
    private val jdbcTemplate: JdbcTemplate,
) {
    fun getTopProducts(): List<TopProductStats> {
        val sql =
            """
            SELECT p.name AS productName, COUNT(*) AS addedCount, MAX(ch.created_at) AS lastAddedAt 
            FROM cart_history ch 
            JOIN cart_item ci ON ch.cart_product_id = ci.id 
            JOIN product p ON ci.product_id = p.id 
            WHERE ch.created_at >= CURRENT_DATE - INTERVAL '30' DAY GROUP BY p.id, p.name 
            ORDER BY addedCount DESC, lastAddedAt DESC LIMIT 5
            """.trimIndent()
        return jdbcTemplate.query(sql) { rs, _ ->
            TopProductStats(
                productName = rs.getString("productName"),
                addedCount = rs.getInt("addedCount"),
                lastAddedAt = rs.getTimestamp("lastAddedAt"),
            )
        }
    }

    fun getTop5ActiveUsers(): List<ActiveUsersResponse> {
        val sql =
            """
            SELECT DISTINCT m.ID AS memberId, m.NAME AS memberName, m.EMAIL AS memberEmail
            FROM MEMBER m
            JOIN CART c ON m.ID = c.MEMBER_ID
            JOIN CART_ITEM ci ON ci.CART_ID = c.ID
            JOIN CART_HISTORY ch ON ch.CART_PRODUCT_ID = ci.ID
            WHERE ch.CREATED_AT >= CURRENT_DATE - INTERVAL '7' DAY
            ORDER BY m.ID
            """.trimIndent()
        return jdbcTemplate.query(sql) { rs, _ ->
            ActiveUsersResponse(
                memberId = rs.getLong("memberId"),
                memberName = rs.getString("memberName"),
                memberEmail = rs.getString("memberEmail"),
            )
        }
    }
}
