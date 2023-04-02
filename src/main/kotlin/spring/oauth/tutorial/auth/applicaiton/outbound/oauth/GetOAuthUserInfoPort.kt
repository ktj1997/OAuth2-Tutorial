package spring.oauth.tutorial.auth.applicaiton.outbound.oauth

import spring.oauth.tutorial.auth.applicaiton.outbound.oauth.model.GetOAuthUserInfoQuery
import spring.oauth.tutorial.auth.applicaiton.outbound.oauth.model.OAuthUserInfo

interface GetOAuthUserInfoPort {
    fun getUserInfo(query: GetOAuthUserInfoQuery): OAuthUserInfo
}
