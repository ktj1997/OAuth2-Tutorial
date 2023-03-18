package spring.oauth.tutorial.auth.adapter.inbound.rest.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "spring.security.oauth2.client")
data class OAuth2Properties(
    val provider:  Map<String,OAuth2ProviderProperties> = mapOf(),
    val registration: Map<String,OAuth2RegistrationProperties> = mapOf()
)

data class OAuth2ProviderProperties(
    val authorizationUri: String,
    val tokenUri: String,
    val userInfoUri:String,
    val userNameAttribute: String,
)

data class OAuth2RegistrationProperties(
    val clientId: String,
    val clientSecret: String,
    val redirectUri: String
)