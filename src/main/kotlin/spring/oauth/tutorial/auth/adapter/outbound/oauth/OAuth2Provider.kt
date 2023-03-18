package spring.oauth.tutorial.auth.adapter.outbound.oauth

import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.security.oauth2.core.AuthorizationGrantType
import org.springframework.security.oauth2.core.ClientAuthenticationMethod
import spring.oauth.tutorial.auth.adapter.inbound.rest.config.properties.OAuth2ProviderProperties
import spring.oauth.tutorial.auth.adapter.inbound.rest.config.properties.OAuth2RegistrationProperties


enum class OAuth2Provider(val registrationName: String) {
    KAKAO("kakao"),

    GOOGLE("google");

    fun getBuilder(
        registrationName: String,
        providerProperties: OAuth2ProviderProperties,
        registrationProperties: OAuth2RegistrationProperties
    ): ClientRegistration.Builder {
        val builder = ClientRegistration.withRegistrationId(registrationName)
        builder.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
        builder.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)

        builder.clientName(registrationName)
        builder.scope("profile", "email")
        builder.authorizationUri(providerProperties.authorizationUri)
        builder.tokenUri(providerProperties.tokenUri)
        builder.userInfoUri(providerProperties.userInfoUri)
        builder.userNameAttributeName(providerProperties.userNameAttribute)

        builder.clientId(registrationProperties.clientId)
        builder.clientSecret(registrationProperties.clientSecret)
        builder.redirectUri(registrationProperties.redirectUri)
        return builder
    }

}