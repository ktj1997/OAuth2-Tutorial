package spring.oauth.tutorial.auth.adapter.outbound.oauth

import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.stereotype.Component
import spring.oauth.tutorial.auth.applicaiton.outbound.rest.GetOAuthRedirectUriPort
import spring.oauth.tutorial.auth.domain.OAuthType

@Component
class GetAuthorizationCodeRedirectUriAdapter(
    private val oAuthClientFactory: OAuthClientFactory,
    private val clientRepository: ClientRegistrationRepository
) : GetOAuthRedirectUriPort {
    override fun getAuthorizationCodeRedirectUri(type: OAuthType): String {
        val registration = clientRepository.findByRegistrationId(type.registrationName)
        val oAuthClient = oAuthClientFactory.findByProvider(type)

        val url = oAuthClient.getAuthorizationCodeRedirectURI(registration)
        println(url)
        return url
    }
}
