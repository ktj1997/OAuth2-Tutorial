package spring.oauth.tutorial.auth.applicaiton.inbound.rest.filter

import spring.oauth.tutorial.auth.domain.TokenType

interface AuthorizeUserUseCase {

    fun parseUserIdentifierFromToken(token: String?, type: TokenType): String
}
