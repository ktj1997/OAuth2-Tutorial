package spring.oauth.tutorial.auth.applicaiton.inbound

import spring.oauth.tutorial.auth.applicaiton.inbound.model.SignInQuery
import spring.oauth.tutorial.auth.applicaiton.inbound.model.SignInResult

interface SignInUseCase {
    fun signIn(query: SignInQuery): SignInResult
}
