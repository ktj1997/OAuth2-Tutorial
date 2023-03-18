package spring.oauth.tutorial.auth.adapter.outbound.oauth

import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.stereotype.Component
import spring.oauth.tutorial.auth.adapter.inbound.rest.config.properties.OAuth2Properties

@Component
class OAuth2ProviderRepository(
    private val oAuth2Properties: OAuth2Properties
) {
    fun getRegistrations() : List<ClientRegistration> {
        val registrations = OAuth2Provider.values()
            .map {provider ->
                provider.getBuilder(
                    registrationName = provider.registrationName,
                    providerProperties = oAuth2Properties.provider[provider.registrationName]!!,
                    registrationProperties = oAuth2Properties.registration[provider.registrationName]!!
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