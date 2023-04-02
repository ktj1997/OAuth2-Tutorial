package spring.oauth.tutorial.auth.applicaiton.outbound.oauth

import spring.oauth.tutorial.auth.applicaiton.outbound.oauth.model.GetOAuthTokenQuery
import spring.oauth.tutorial.auth.applicaiton.outbound.oauth.model.OAuthTokenInfo

interface GetOAuthTokenPort {

    fun getToken(query: GetOAuthTokenQuery): OAuthTokenInfo
}
