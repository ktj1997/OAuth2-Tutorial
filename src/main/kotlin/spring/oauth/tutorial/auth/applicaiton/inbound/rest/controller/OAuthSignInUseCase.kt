package spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller

import spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller.model.OAuthSignInUseCaseInput
import spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller.model.OAuthSignInUseCaseOutput
import spring.oauth.tutorial.auth.domain.OAuthType

interface OAuthSignInUseCase {
    fun oAuthSignIn(query: OAuthSignInUseCaseInput): OAuthSignInUseCaseOutput
    fun getRedirectURI(type: OAuthType): String
}
