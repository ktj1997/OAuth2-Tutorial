package spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller

import spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller.model.SignInUseCaseInput
import spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller.model.SignInUseCaseOutput

interface SignInUseCase {
    fun signIn(query: SignInUseCaseInput): SignInUseCaseOutput
}
