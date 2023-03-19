package spring.oauth.tutorial.auth.applicaiton.service

import org.springframework.stereotype.Service
import spring.oauth.tutorial.auth.applicaiton.inbound.OAuthSignInUseCase
import spring.oauth.tutorial.auth.applicaiton.inbound.model.OAuthSignInQuery
import spring.oauth.tutorial.auth.applicaiton.inbound.model.OAuthSignInResult
import spring.oauth.tutorial.auth.applicaiton.outbound.rest.GetOAuthTokenPort

@Service
class OAuthSignInService(
    private val getOAuthTokenPort: GetOAuthTokenPort
) : OAuthSignInUseCase {
    override fun oAuthSignIn(query: OAuthSignInQuery): OAuthSignInResult {
       val token = getOAuthTokenPort.getToken(query)
        return OAuthSignInResult(token)
    }
}