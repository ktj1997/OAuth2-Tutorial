package spring.oauth.tutorial.auth.applicaiton.service

import org.springframework.stereotype.Service
import spring.oauth.tutorial.auth.applicaiton.inbound.rest.filter.AuthorizeUserUseCase
import spring.oauth.tutorial.auth.applicaiton.outbound.jwt.ResolveTokenPort
import spring.oauth.tutorial.auth.domain.TokenType

@Service
class AuthorizeUserService(
    val resolveTokenPort: ResolveTokenPort
) : AuthorizeUserUseCase {
    override fun parseUserIdentifierFromToken(token: String, type: TokenType): String {
        return resolveTokenPort.getUserIdentifier(
            token = token,
            type = type
        )
    }
}
