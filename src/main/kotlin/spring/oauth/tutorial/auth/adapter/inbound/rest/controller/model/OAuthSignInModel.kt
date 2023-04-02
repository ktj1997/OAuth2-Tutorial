package spring.oauth.tutorial.auth.adapter.inbound.rest.controller.model

import spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller.model.OAuthSignInUseCaseInput
import spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller.model.OAuthSignInUseCaseOutput
import spring.oauth.tutorial.auth.domain.OAuthType

data class OAuthSignInRequest(
    val authorizationCode: String,
    val provider: String
) {

    fun toQuery(): OAuthSignInUseCaseInput {
        try {
            return OAuthSignInUseCaseInput(
                provider = OAuthType.valueOf(provider),
                authorizationCode = authorizationCode
            )
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("$provider is not supported oAuthType")
        }
    }
}

data class OAuthSignInResponse(
    val token: String
) {
    companion object {
        fun fromResult(result: OAuthSignInUseCaseOutput): OAuthSignInResponse {
            return OAuthSignInResponse(
                token = result.token
            )
        }
    }
}
