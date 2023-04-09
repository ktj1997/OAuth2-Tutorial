package spring.oauth.tutorial.auth.adapter.outbound.oauth

import org.springframework.stereotype.Component
import spring.oauth.tutorial.auth.applicaiton.outbound.oauth.GetOAuthRedirectUriPort
import spring.oauth.tutorial.auth.domain.OAuthType

@Component
class GetAuthorizationCodeRedirectUriAdapter(
    private val oAuthClientMap: OAuthClientMap,
) : GetOAuthRedirectUriPort {
    override fun getAuthorizationCodeRedirectUri(type: OAuthType): String {
        val oAuthClient = oAuthClientMap.findByProvider(type)
        return oAuthClient.getAuthorizationCodeRedirectURI()
    }
}
