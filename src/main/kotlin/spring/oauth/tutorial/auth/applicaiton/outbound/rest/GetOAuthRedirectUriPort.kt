package spring.oauth.tutorial.auth.applicaiton.outbound.rest

import spring.oauth.tutorial.auth.domain.OAuthType

interface GetOAuthRedirectUriPort {
    fun getAuthorizationCodeRedirectUri(type: OAuthType): String
}
