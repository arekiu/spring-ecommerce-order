package ecommerce.service

import ecommerce.configuration.JwtTokenProvider
import ecommerce.configuration.PasswordEncoder
import ecommerce.dto.LoginRequest
import ecommerce.dto.RegistrationRequest
import ecommerce.dto.TokenResponse
import ecommerce.exception.EmailOrPasswordIncorrectException
import ecommerce.exception.MemberEmailAlreadyExistsException
import ecommerce.model.Cart
import ecommerce.model.Member
import ecommerce.repository.CartJpaRepository
import ecommerce.repository.MemberJpaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthenticationService(
    private val tokenService: JwtTokenProvider,
    private val passwordEncoder: PasswordEncoder,
    private val memberJpaRepository: MemberJpaRepository,
    private val cartJpaRepository: CartJpaRepository,
) {
    @Transactional
    fun registration(request: RegistrationRequest): TokenResponse {
        if (memberJpaRepository.existsByEmail(request.email)) {
            throw MemberEmailAlreadyExistsException("Email already exists: ${request.email}")
        }
        val hashedPassword = passwordEncoder.encode(request.password)
        val member =
            Member(
                name = request.name,
                email = request.email,
                password = hashedPassword,
                role = "USER",
            )
        cartJpaRepository.save(Cart(member))
        val token = tokenService.createToken(request.email)
        return TokenResponse(token)
    }

    fun logIn(request: LoginRequest): TokenResponse {
        val member = memberJpaRepository.findByEmail(request.email) ?: throw EmailOrPasswordIncorrectException("Invalid password for email")

        if (!passwordEncoder.matches(request.password, member.password)) {
            throw EmailOrPasswordIncorrectException("Invalid password for email")
        }
        val token = tokenService.createToken(request.email)
        return TokenResponse(token)
    }
}
