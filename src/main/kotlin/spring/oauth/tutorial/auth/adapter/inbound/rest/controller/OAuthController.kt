package spring.oauth.tutorial.auth.adapter.inbound.rest.controller

import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import spring.oauth.tutorial.auth.adapter.inbound.rest.controller.model.OAuthSignInResponse
import spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller.OAuthSignInUseCase
import spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller.model.OAuthSignInQuery
import spring.oauth.tutorial.auth.domain.OAuthType

@RestController
@RequestMapping("/api/v1/auth")
class OAuthController(
    private val oAuthSignInUseCase: OAuthSignInUseCase
) {

    @GetMapping("/signin/oauth")
    fun oAuthSignIn(
        @RequestParam type: OAuthType,
        response: HttpServletResponse
    ) {
        response.sendRedirect(oAuthSignInUseCase.getRedirectURI(type))
    }

    @GetMapping("/signin/oauth/kakao")
    fun kakaoSignIn(
        @RequestParam code: String
    ): OAuthSignInResponse {
        val query = OAuthSignInQuery(
            authorizationCode = code,
            provider = OAuthType.KAKAO
        )
        val result = oAuthSignInUseCase.oAuthSignIn(query)

        return OAuthSignInResponse.fromResult(result)
    }

    @GetMapping("/signin/oauth/google")
    fun googleSignIn(
        @RequestParam code: String
    ): OAuthSignInResponse {
        val query = OAuthSignInQuery(
            authorizationCode = code,
            provider = OAuthType.GOOGLE
        )
        val result = oAuthSignInUseCase.oAuthSignIn(query)

        return OAuthSignInResponse.fromResult(result)
    }
}
