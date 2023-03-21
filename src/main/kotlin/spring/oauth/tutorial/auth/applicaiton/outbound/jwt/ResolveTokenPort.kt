package spring.oauth.tutorial.auth.applicaiton.outbound.jwt

import spring.oauth.tutorial.auth.domain.TokenType

interface ResolveTokenPort {

    fun getUserIdentifier(token: String, type: TokenType): String
}
