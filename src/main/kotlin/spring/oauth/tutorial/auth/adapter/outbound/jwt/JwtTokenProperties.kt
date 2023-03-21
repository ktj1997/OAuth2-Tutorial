package spring.oauth.tutorial.auth.adapter.outbound.jwt

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(value = "jwt")
data class JwtTokenProperties(
   val access: TokenProperties,
    val refresh: TokenProperties
){
    data class TokenProperties(
        val expire:Long,
        val secret:String
    )
}
