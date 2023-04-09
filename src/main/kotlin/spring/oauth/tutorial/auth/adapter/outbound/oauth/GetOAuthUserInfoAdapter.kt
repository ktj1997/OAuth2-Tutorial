package spring.oauth.tutorial.auth.adapter.outbound.oauth

import org.springframework.stereotype.Component
import spring.oauth.tutorial.auth.applicaiton.outbound.oauth.GetOAuthUserInfoPort
import spring.oauth.tutorial.auth.applicaiton.outbound.oauth.model.GetOAuthUserInfoQuery
import spring.oauth.tutorial.auth.applicaiton.outbound.oauth.model.OAuthUserInfo

@Component
class GetOAuthUserInfoAdapter(
    private val oAuthClientMap: OAuthClientMap,
) : GetOAuthUserInfoPort {

    override fun getUserInfo(query: GetOAuthUserInfoQuery): OAuthUserInfo {
        val oAuthClient = oAuthClientMap.findByProvider(query.provider)

        return oAuthClient.getUserInfo(query)
    }
}
