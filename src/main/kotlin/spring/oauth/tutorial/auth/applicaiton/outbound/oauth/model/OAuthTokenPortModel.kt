package spring.oauth.tutorial.auth.applicaiton.outbound.oauth.model

import spring.oauth.tutorial.auth.domain.OAuthType

data class GetOAuthTokenQuery(
    val provider: OAuthType,
    val authorizationCode: String
)
data class OAuthTokenInfo(
    val accessToken: String,
    val refreshToken: String,
    val idToken: String?
)
