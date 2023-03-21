package spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller

import spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller.model.SignInQuery
import spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller.model.SignInResult

interface SignInUseCase {
    fun signIn(query: SignInQuery): SignInResult
}
