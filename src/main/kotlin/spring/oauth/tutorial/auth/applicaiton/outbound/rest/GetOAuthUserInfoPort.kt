package spring.oauth.tutorial.auth.applicaiton.outbound.rest

import spring.oauth.tutorial.auth.applicaiton.outbound.rest.model.OAuthUserInfo
import spring.oauth.tutorial.auth.domain.OAuthType

interface GetOAuthUserInfoPort {
    fun getUserInfo(provider: OAuthType, accessToken: String): OAuthUserInfo
}
