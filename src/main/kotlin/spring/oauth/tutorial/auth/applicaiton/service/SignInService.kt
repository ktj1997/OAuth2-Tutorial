package spring.oauth.tutorial.auth.applicaiton.service

import org.springframework.stereotype.Service
import spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller.SignInUseCase
import spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller.model.SignInQuery
import spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller.model.SignInResult

@Service
class SignInService : SignInUseCase {
    override fun signIn(query: SignInQuery): SignInResult {
        TODO("Not yet implemented")
    }
}
