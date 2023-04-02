package spring.oauth.tutorial.auth.applicaiton.outbound.oauth

import spring.oauth.tutorial.auth.domain.OAuthType

interface GetOAuthRedirectUriPort {
    fun getAuthorizationCodeRedirectUri(type: OAuthType): String
}
