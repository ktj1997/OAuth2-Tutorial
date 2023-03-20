package spring.oauth.tutorial.auth.adapter.inbound.rest

import SignInRequest
import SignInResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import spring.oauth.tutorial.auth.adapter.inbound.rest.model.OAuthSignInRequest
import spring.oauth.tutorial.auth.adapter.inbound.rest.model.OAuthSignInResponse
import spring.oauth.tutorial.auth.applicaiton.inbound.OAuthSignInUseCase
import spring.oauth.tutorial.auth.applicaiton.inbound.SignInUseCase

@RestController
@RequestMapping("/api/v1/auth")
class SignInController(
    val signInUseCase: SignInUseCase,
    val oAuthSignInUseCase: OAuthSignInUseCase
) {

    @PostMapping("/signin")
    fun signIn(
        @RequestBody request: SignInRequest,
    ): SignInResponse {
        val query = request.toQuery()
        val result = signInUseCase.signIn(query)

        return SignInResponse.fromResult(result)
    }

    @PostMapping("/signin/oauth")
    fun oAuthSignIn(
        @RequestBody request: OAuthSignInRequest
    ): OAuthSignInResponse {
        val query = request.toQuery()
        val result = oAuthSignInUseCase.oAuthSignIn(query)

        return OAuthSignInResponse.fromResult(result)
    }
}
