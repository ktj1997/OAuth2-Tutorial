package spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller.model

import spring.oauth.tutorial.auth.domain.OAuthType

data class OAuthSignInUseCaseInput(
    val authorizationCode: String,
    val provider: OAuthType
)

data class OAuthSignInUseCaseOutput(
    val token: String
)
