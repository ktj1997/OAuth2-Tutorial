package spring.oauth.tutorial.auth.adapter.outbound.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.Date
import javax.crypto.SecretKey
import org.springframework.stereotype.Component
import spring.oauth.tutorial.auth.applicaiton.outbound.jwt.GenerateTokenPort
import spring.oauth.tutorial.auth.domain.TokenType

@Component
class GenerateTokenAdapter(
    val jwtTokenProperties: JwtTokenProperties
) : GenerateTokenPort {
    companion object {
        val BEARER = "Bearer "
    }

    override fun generateToken(userIdentifier: String, tokenType: TokenType): String {
        return if (tokenType === TokenType.ACCESS) {
            generateAccessToken(
                userIdentifier = userIdentifier,
                expire = jwtTokenProperties.access.expire,
                secret = jwtTokenProperties.access.secret
            )
        } else {
            generateAccessToken(
                userIdentifier = userIdentifier,
                expire = jwtTokenProperties.refresh.expire,
                secret = jwtTokenProperties.refresh.secret
            )
        }
    }

    private fun generateAccessToken(userIdentifier: String, expire: Long, secret: String): String {
        val key: SecretKey = Keys.hmacShaKeyFor(secret.toByteArray())
        val now = ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toInstant().toEpochMilli()
        val expiredAt = now + expire

        return Jwts.builder()
            .claim("id", userIdentifier)
            .setIssuedAt(Date(now))
            .setExpiration(Date(expiredAt))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }
}
