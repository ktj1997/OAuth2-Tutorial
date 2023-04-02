package spring.oauth.tutorial.auth.adapter.outbound.oauth

import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.stereotype.Component
import spring.oauth.tutorial.auth.applicaiton.outbound.oauth.GetOAuthUserInfoPort
import spring.oauth.tutorial.auth.applicaiton.outbound.oauth.model.GetOAuthUserInfoQuery
import spring.oauth.tutorial.auth.applicaiton.outbound.oauth.model.OAuthUserInfo

@Component
class GetOAuthUserInfoAdapter(
    private val oAuthClientFactory: OAuthClientFactory,
    private val clientRepository: ClientRegistrationRepository
) : GetOAuthUserInfoPort {

    override fun getUserInfo(query: GetOAuthUserInfoQuery): OAuthUserInfo {
        val registration = clientRepository.findByRegistrationId(query.provider.registrationName)
        val oAuthClient = oAuthClientFactory.findByProvider(query.provider)

        return oAuthClient.getUserInfo(query, registration)
    }
}
