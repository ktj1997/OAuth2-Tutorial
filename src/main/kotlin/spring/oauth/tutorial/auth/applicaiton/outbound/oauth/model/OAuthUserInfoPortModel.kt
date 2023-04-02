package spring.oauth.tutorial.auth.applicaiton.outbound.oauth.model

import spring.oauth.tutorial.auth.domain.OAuthType

data class OAuthUserInfo(
    val email: String
)
data class GetOAuthUserInfoQuery(
    val provider: OAuthType,
    val accessToken: String,
    val refreshToken: String,
    val idToken: String?
)
