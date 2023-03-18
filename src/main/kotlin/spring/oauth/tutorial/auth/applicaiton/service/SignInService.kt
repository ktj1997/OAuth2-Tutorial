package spring.oauth.tutorial.auth.applicaiton.service

import org.springframework.stereotype.Service
import spring.oauth.tutorial.auth.applicaiton.inbound.SignInUseCase
import spring.oauth.tutorial.auth.applicaiton.inbound.model.SignInQuery
import spring.oauth.tutorial.auth.applicaiton.inbound.model.SignInResult

@Service
class SignInService : SignInUseCase {
    override fun signIn(query: SignInQuery): SignInResult {
        TODO("Not yet implemented")
    }
}