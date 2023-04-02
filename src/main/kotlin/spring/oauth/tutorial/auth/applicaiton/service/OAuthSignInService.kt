package spring.oauth.tutorial.auth.applicaiton.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller.OAuthSignInUseCase
import spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller.SignUpUseCase
import spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller.model.OAuthSignInUseCaseInput
import spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller.model.OAuthSignInUseCaseOutput
import spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller.model.SignUpUseCaseInput
import spring.oauth.tutorial.auth.applicaiton.outbound.jwt.GenerateTokenPort
import spring.oauth.tutorial.auth.applicaiton.outbound.persistence.GetAccountPort
import spring.oauth.tutorial.auth.applicaiton.outbound.oauth.GetOAuthRedirectUriPort
import spring.oauth.tutorial.auth.applicaiton.outbound.oauth.GetOAuthTokenPort
import spring.oauth.tutorial.auth.applicaiton.outbound.oauth.GetOAuthUserInfoPort
import spring.oauth.tutorial.auth.applicaiton.outbound.oauth.model.GetOAuthTokenQuery
import spring.oauth.tutorial.auth.applicaiton.outbound.oauth.model.GetOAuthUserInfoQuery
import spring.oauth.tutorial.auth.domain.OAuthType
import spring.oauth.tutorial.auth.domain.TokenType

@Service
class OAuthSignInService(
    private val signUpUseCase: SignUpUseCase,
    private val getAccountPort: GetAccountPort,
    private val generateTokenPort: GenerateTokenPort,
    private val getOAuthTokenPort: GetOAuthTokenPort,
    private val getOAuthUserInfoPort: GetOAuthUserInfoPort,
    private val getOAuthRedirectUriPort: GetOAuthRedirectUriPort
) : OAuthSignInUseCase {
    @Transactional
    override fun oAuthSignIn(input: OAuthSignInUseCaseInput): OAuthSignInUseCaseOutput {
        val getOAuthTokenQuery = GetOAuthTokenQuery(
            provider = input.provider,
            authorizationCode = input.authorizationCode
        )
        val oAuthTokenInfo = getOAuthTokenPort.getToken(getOAuthTokenQuery)

        val getOAuthUserInfoQuery = GetOAuthUserInfoQuery(
            provider = input.provider,
            accessToken = oAuthTokenInfo.accessToken,
            refreshToken = oAuthTokenInfo.refreshToken,
            idToken = oAuthTokenInfo.idToken
        )
        val userInfo = getOAuthUserInfoPort.getUserInfo(getOAuthUserInfoQuery)

        val account =
            getAccountPort.findAccountByEmailAndOAuthType(userInfo.email, input.provider)
                ?: run {
                    val command = SignUpUseCaseInput(
                        userName = null,
                        password = null,
                        oAuthType = input.provider,
                        email = userInfo.email
                    )
                    signUpUseCase.signUp(command)
                }

        val accessToken = generateTokenPort.generateToken(account.userIdentifier, TokenType.ACCESS)
        // refreshToken 생성
        return OAuthSignInUseCaseOutput(accessToken)
    }

    override fun getRedirectURI(type: OAuthType): String {
        return getOAuthRedirectUriPort.getAuthorizationCodeRedirectUri(type)
    }
}
