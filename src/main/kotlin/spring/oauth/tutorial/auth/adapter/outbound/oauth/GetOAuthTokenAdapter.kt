package spring.oauth.tutorial.auth.adapter.outbound.oauth

import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.stereotype.Component
import spring.oauth.tutorial.auth.applicaiton.outbound.oauth.GetOAuthTokenPort
import spring.oauth.tutorial.auth.applicaiton.outbound.oauth.model.GetOAuthTokenQuery
import spring.oauth.tutorial.auth.applicaiton.outbound.oauth.model.OAuthTokenInfo

@Component
class GetOAuthTokenAdapter(
    private val oAuthClientFactory: OAuthClientFactory,
    private val clientRepository: ClientRegistrationRepository
) : GetOAuthTokenPort {
    override fun getToken(query: GetOAuthTokenQuery): OAuthTokenInfo {
        val registration = clientRepository.findByRegistrationId(query.provider.registrationName)
        val oAuthClient = oAuthClientFactory.findByProvider(query.provider)

        return oAuthClient.getToken(
            registration = registration,
            authorizationCode = query.authorizationCode
        )
    }
}
