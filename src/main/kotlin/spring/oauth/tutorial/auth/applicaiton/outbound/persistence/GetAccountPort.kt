package spring.oauth.tutorial.auth.applicaiton.outbound.persistence

import spring.oauth.tutorial.auth.domain.Account
import spring.oauth.tutorial.auth.domain.OAuthType

interface GetAccountPort {

    fun findAccountByEmailAndOAuthType(email: String, oAuthType: OAuthType): Account?
}
