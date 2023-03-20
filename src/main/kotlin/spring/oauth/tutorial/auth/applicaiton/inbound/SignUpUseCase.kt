package spring.oauth.tutorial.auth.applicaiton.inbound

import spring.oauth.tutorial.auth.applicaiton.inbound.model.SignUpCommand
import spring.oauth.tutorial.auth.domain.Account

interface SignUpUseCase {

    fun signUp(command: SignUpCommand): Account
}
