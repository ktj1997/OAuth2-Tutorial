package spring.oauth.tutorial.auth.applicaiton.service

import org.springframework.stereotype.Service
import spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller.SignInUseCase
import spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller.model.SignInUseCaseInput
import spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller.model.SignInUseCaseOutput

@Service
class SignInService : SignInUseCase {
    override fun signIn(query: SignInUseCaseInput): SignInUseCaseOutput {
        TODO("Not yet implemented")
    }
}
