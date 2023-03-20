package spring.oauth.tutorial.auth.applicaiton.inbound.model

import spring.oauth.tutorial.auth.domain.OAuthType

data class OAuthSignInQuery(
    val authorizationCode: String,
    val provider: OAuthType
)

data class OAuthSignInResult(
    val token: String
)
