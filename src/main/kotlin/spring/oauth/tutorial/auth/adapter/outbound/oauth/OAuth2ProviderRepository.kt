package spring.oauth.tutorial.auth.adapter.outbound.oauth

import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties
import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.stereotype.Component

@Component
class OAuth2ProviderRepository(
    private val oAuth2ClientProperties: OAuth2ClientProperties
) {
    fun getRegistrations() : List<ClientRegistration> {
        val registrations = OAuth2Provider.values()
            .map {oAuthProvider ->
                oAuthProvider.getBuilder(
                    providerName = oAuthProvider.providerName,
                    provider = oAuth2ClientProperties.provider[oAuthProvider.providerName]!!,
                    registration = oAuth2ClientProperties.registration[oAuthProvider.providerName]!!
                ).build() }
            .toList()

        for(registration in registrations){
            println(registration.registrationId)
            println(registration.clientId)
            println(registration.clientName)
            println(registration.clientSecret)
            println(registration.authorizationGrantType)
            println(registration.scopes.toString())
            println(registration.redirectUri)
            println(registration.providerDetails)
        }
        return registrations
    }
}