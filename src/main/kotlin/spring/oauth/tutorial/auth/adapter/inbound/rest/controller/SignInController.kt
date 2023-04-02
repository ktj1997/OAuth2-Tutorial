package spring.oauth.tutorial.auth.adapter.inbound.rest.controller

import SignInRequest
import SignInResponse
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller.SignInUseCase

@RestController
@RequestMapping("/api/v1/auth")
class SignInController(
    val signInUseCase: SignInUseCase,
) {

    @PostMapping("/signin")
    fun signIn(
        @RequestBody request: SignInRequest,
        response: HttpServletResponse
    ): SignInResponse {
        val query = request.toQuery()
        val result = signInUseCase.signIn(query)

        return SignInResponse.fromResult(result)
    }
}
