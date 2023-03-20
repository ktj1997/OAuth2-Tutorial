package spring.oauth.tutorial.auth.adapter.outbound.rest

import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.stereotype.Component
import spring.oauth.tutorial.auth.adapter.outbound.rest.client.model.OAuthUserInfo
import spring.oauth.tutorial.auth.applicaiton.outbound.rest.GetOAuthUserInfoPort
import spring.oauth.tutorial.auth.domain.OAuthType

@Component
class GetOAuthUserInfoAdapter(
    private val oAuthClientFactory: OAuthClientFactory,
    private val clientRepository: ClientRegistrationRepository
) : GetOAuthUserInfoPort {
    override fun getUserInfo(provider: OAuthType, accessToken: String): OAuthUserInfo {
        val registration = clientRepository.findByRegistrationId(provider.registrationName)
        val oAuthClient = oAuthClientFactory.findByProvider(provider)

        return oAuthClient.getUserInfo(accessToken, registration)
    }
}
