package spring.oauth.tutorial.auth.adapter.outbound.oauth.client

import spring.oauth.tutorial.auth.applicaiton.outbound.oauth.model.GetOAuthUserInfoQuery
import spring.oauth.tutorial.auth.applicaiton.outbound.oauth.model.OAuthTokenInfo
import spring.oauth.tutorial.auth.applicaiton.outbound.oauth.model.OAuthUserInfo
import spring.oauth.tutorial.auth.domain.OAuthType

interface OAuthClient {
    fun getToken(authorizationCode: String): OAuthTokenInfo
    fun getUserInfo(query: GetOAuthUserInfoQuery): OAuthUserInfo
    fun getProvider(): OAuthType
    fun getAuthorizationCodeRedirectURI(): String
}
