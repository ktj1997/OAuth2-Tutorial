package spring.oauth.tutorial.auth.adapter.outbound.rest

import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.stereotype.Component
import spring.oauth.tutorial.auth.applicaiton.inbound.model.OAuthSignInQuery
import spring.oauth.tutorial.auth.applicaiton.outbound.rest.GetOAuthTokenPort

@Component
class GetOAuthTokenAdapter(
    private val oAuthClientFactory: OAuthClientFactory,
    private val clientRepository: ClientRegistrationRepository
) : GetOAuthTokenPort {
    override fun getToken(query: OAuthSignInQuery): String {
        val registration = clientRepository.findByRegistrationId(query.provider.registrationName)
        val oAuthClient = oAuthClientFactory.findByProvider(query.provider)

        val oAuthTokenResponse = oAuthClient.getToken(
            registration = registration,
            authorizationCode = query.authorizationCode
        )

        return oAuthTokenResponse.token
    }
}
