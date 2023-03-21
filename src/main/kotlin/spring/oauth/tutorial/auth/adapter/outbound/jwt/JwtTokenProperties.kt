package spring.oauth.tutorial.auth.adapter.outbound.jwt

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(value = "jwt")
data class JwtTokenProperties(
    val accessTokenExpire: Long,
    val accessTokenSecret: String,
    val secretTokenExpire: Long,
    val secretTokenSecret: String
)
