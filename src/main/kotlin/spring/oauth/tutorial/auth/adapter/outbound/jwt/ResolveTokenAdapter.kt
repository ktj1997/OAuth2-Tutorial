package spring.oauth.tutorial.auth.adapter.outbound.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.DecodingException
import io.jsonwebtoken.security.Keys
import javax.crypto.SecretKey
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import spring.oauth.tutorial.auth.applicaiton.outbound.jwt.ResolveTokenPort
import spring.oauth.tutorial.auth.domain.TokenType

@Component
class ResolveTokenAdapter(
    val jwtTokenProperties: JwtTokenProperties
) : ResolveTokenPort {

    companion object {
        const val BEARER = "Bearer "
    }
    override fun getUserIdentifier(token: String, type: TokenType): String {
        if (type === TokenType.ACCESS) {
            val key: SecretKey = Keys.hmacShaKeyFor(jwtTokenProperties.access.secret.toByteArray())
            val jwt = token.let {
                if (it.startsWith(BEARER)) {
                    token.replace(BEARER, "")
                } else {
                    throw DecodingException("Bearer토큰이 아닙니다.")
                }
            }

            val claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt)
                .body

            return claims["id"].toString()
        }
        throw IllegalArgumentException("Cannot Parse Token")
    }

    fun getAuthentication(claims: Claims): Authentication {
        return UsernamePasswordAuthenticationToken(
            claims["id"].toString(),
            "", null
        )
    }
}
