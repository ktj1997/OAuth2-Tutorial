package spring.oauth.tutorial.auth.applicaiton.service

import org.springframework.stereotype.Service
import spring.oauth.tutorial.auth.applicaiton.inbound.OAuthSignInUseCase
import spring.oauth.tutorial.auth.applicaiton.inbound.model.OAuthSignInQuery
import spring.oauth.tutorial.auth.applicaiton.inbound.model.OAuthSignInResult
import spring.oauth.tutorial.auth.applicaiton.outbound.rest.GetOAuthTokenPort
import spring.oauth.tutorial.auth.applicaiton.outbound.rest.GetOAuthUserInfoPort

@Service
class OAuthSignInService(
    private val getOAuthTokenPort: GetOAuthTokenPort,
    private val getOAuthUserInfoPort: GetOAuthUserInfoPort
) : OAuthSignInUseCase {
    override fun oAuthSignIn(query: OAuthSignInQuery): OAuthSignInResult {
        val token = getOAuthTokenPort.getToken(query)
        val userInfo = getOAuthUserInfoPort.getUserInfo(
            provider = query.provider,
            accessToken = token
        )

        println(userInfo)
        return OAuthSignInResult(token)
    }
}