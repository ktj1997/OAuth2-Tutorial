package spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller

import spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller.model.OAuthSignInQuery
import spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller.model.OAuthSignInResult
import spring.oauth.tutorial.auth.domain.OAuthType

interface OAuthSignInUseCase {
    fun oAuthSignIn(query: OAuthSignInQuery): OAuthSignInResult
    fun getRedirectURI(type: OAuthType): String
}
