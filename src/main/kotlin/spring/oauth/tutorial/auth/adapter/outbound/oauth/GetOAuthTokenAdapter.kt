package spring.oauth.tutorial.auth.adapter.outbound.oauth

import org.springframework.stereotype.Component
import spring.oauth.tutorial.auth.applicaiton.outbound.oauth.GetOAuthTokenPort
import spring.oauth.tutorial.auth.applicaiton.outbound.oauth.model.GetOAuthTokenQuery
import spring.oauth.tutorial.auth.applicaiton.outbound.oauth.model.OAuthTokenInfo

@Component
class GetOAuthTokenAdapter(
    private val oAuthClientMap: OAuthClientMap
) : GetOAuthTokenPort {
    override fun getToken(query: GetOAuthTokenQuery): OAuthTokenInfo {
        val oAuthClient = oAuthClientMap.findByProvider(query.provider)

        return oAuthClient.getToken(
            authorizationCode = query.authorizationCode
        )
    }
}
