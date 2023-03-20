package spring.oauth.tutorial.auth.applicaiton.outbound.rest

import spring.oauth.tutorial.auth.applicaiton.inbound.model.OAuthSignInQuery

interface GetOAuthTokenPort {

    fun getToken(query: OAuthSignInQuery): String
}
