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

    override fun getUserIdentifier(token: String?, type: TokenType): String {
        if (type === TokenType.ACCESS) {
            val key: SecretKey = Keys.hmacShaKeyFor(jwtTokenProperties.accessTokenSecret.toByteArray())
            val jwt = token?.let {
                if (it.startsWith("Bearer ")) {
                    token.replace("Bearer ", "")
                } else {
                    throw DecodingException("Bearer토큰이 아닙니다.")
                }
            } ?: throw IllegalArgumentException("토큰이 존재하지 않습니다.")

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
