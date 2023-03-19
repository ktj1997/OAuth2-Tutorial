package spring.oauth.tutorial.auth.applicaiton.inbound

import spring.oauth.tutorial.auth.applicaiton.inbound.model.OAuthSignInQuery
import spring.oauth.tutorial.auth.applicaiton.inbound.model.OAuthSignInResult

interface OAuthSignInUseCase {
        fun oAuthSignIn(query: OAuthSignInQuery) : OAuthSignInResult
}