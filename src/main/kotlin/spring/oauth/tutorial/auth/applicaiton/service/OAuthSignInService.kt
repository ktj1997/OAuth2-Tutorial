package spring.oauth.tutorial.auth.applicaiton.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import spring.oauth.tutorial.auth.applicaiton.inbound.OAuthSignInUseCase
import spring.oauth.tutorial.auth.applicaiton.inbound.SignUpUseCase
import spring.oauth.tutorial.auth.applicaiton.inbound.model.OAuthSignInQuery
import spring.oauth.tutorial.auth.applicaiton.inbound.model.OAuthSignInResult
import spring.oauth.tutorial.auth.applicaiton.inbound.model.SignUpCommand
import spring.oauth.tutorial.auth.applicaiton.outbound.persistence.GetAccountPort
import spring.oauth.tutorial.auth.applicaiton.outbound.rest.GetOAuthTokenPort
import spring.oauth.tutorial.auth.applicaiton.outbound.rest.GetOAuthUserInfoPort

@Service
class OAuthSignInService(
    private val signUpUseCase: SignUpUseCase,

    private val getOAuthTokenPort: GetOAuthTokenPort,
    private val getOAuthUserInfoPort: GetOAuthUserInfoPort,
    private val getAccountPort: GetAccountPort,
) : OAuthSignInUseCase {
    @Transactional
    override fun oAuthSignIn(query: OAuthSignInQuery): OAuthSignInResult {
        val token = getOAuthTokenPort.getToken(query)
        val userInfo = getOAuthUserInfoPort.getUserInfo(
            provider = query.provider,
            accessToken = token
        )
        val account =
            getAccountPort.findAccountByEmailAndOAuthType(userInfo.email, query.provider)
                ?: run {
                    val command = SignUpCommand(
                        userName = null,
                        password = null,
                        oAuthType = query.provider,
                        email = userInfo.email
                    )
                    signUpUseCase.signUp(command)
                }

        return OAuthSignInResult(token)
    }
}
