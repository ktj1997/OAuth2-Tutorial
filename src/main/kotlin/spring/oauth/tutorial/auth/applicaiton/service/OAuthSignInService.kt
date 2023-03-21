package spring.oauth.tutorial.auth.applicaiton.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller.OAuthSignInUseCase
import spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller.SignUpUseCase
import spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller.model.OAuthSignInQuery
import spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller.model.OAuthSignInResult
import spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller.model.SignUpCommand
import spring.oauth.tutorial.auth.applicaiton.outbound.jwt.GenerateTokenPort
import spring.oauth.tutorial.auth.applicaiton.outbound.persistence.GetAccountPort
import spring.oauth.tutorial.auth.applicaiton.outbound.rest.GetOAuthTokenPort
import spring.oauth.tutorial.auth.applicaiton.outbound.rest.GetOAuthUserInfoPort
import spring.oauth.tutorial.auth.domain.TokenType

@Service
class OAuthSignInService(
    private val signUpUseCase: SignUpUseCase,
    private val generateTokenPort: GenerateTokenPort,
    private val getOAuthTokenPort: GetOAuthTokenPort,
    private val getOAuthUserInfoPort: GetOAuthUserInfoPort,
    private val getAccountPort: GetAccountPort,
) : OAuthSignInUseCase {
    @Transactional
    override fun oAuthSignIn(query: OAuthSignInQuery): OAuthSignInResult {
        val oAuthAccessToken = getOAuthTokenPort.getToken(query)
        val userInfo = getOAuthUserInfoPort.getUserInfo(
            provider = query.provider,
            accessToken = oAuthAccessToken
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


        val accessToken = generateTokenPort.generateToken(account.userIdentifier,TokenType.ACCESS)
        //refreshToken 생성
        return OAuthSignInResult(accessToken)
    }
}
