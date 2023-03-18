package spring.oauth.tutorial.auth.adapter.outbound.oauth

import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties
import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.security.oauth2.core.AuthorizationGrantType
import org.springframework.security.oauth2.core.ClientAuthenticationMethod

enum class OAuth2Provider(val providerName: String) {
    KAKAO("kakao"),

    GOOGLE("google");

    fun getBuilder(
        providerName: String,
        provider: OAuth2ClientProperties.Provider,
        registration: OAuth2ClientProperties.Registration
    ): ClientRegistration.Builder {
        val builder = ClientRegistration.withRegistrationId(providerName)
        builder.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
        builder.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)

        builder.clientName(providerName)
        builder.scope("profile", "email")
        builder.authorizationUri(provider.authorizationUri)
        builder.tokenUri(provider.tokenUri)
        builder.userInfoUri(provider.userInfoUri)
        builder.userNameAttributeName(provider.userNameAttribute)

        builder.clientId(registration.clientId)
        builder.clientSecret(registration.clientSecret)
        builder.redirectUri(registration.redirectUri)
        return builder
    }

}