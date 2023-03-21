package spring.oauth.tutorial.auth.adapter.outbound.rest.client

import OAuthTokenResponse
import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.stereotype.Component
import spring.oauth.tutorial.auth.applicaiton.outbound.rest.model.OAuthUserInfo
import spring.oauth.tutorial.auth.domain.OAuthType

@Component
class GoogleOAuthClient : OAuthClient {
    override fun getToken(
        registration: ClientRegistration,
        authorizationCode: String
    ): OAuthTokenResponse {
        TODO("Not yet implemented")
    }

    override fun getUserInfo(accessToken: String, registration: ClientRegistration): OAuthUserInfo {
        TODO("Not yet implemented")
    }

    override fun getProvider(): OAuthType {
        return OAuthType.GOOGLE
    }
}
