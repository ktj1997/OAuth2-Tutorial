package spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller

import spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller.model.SignUpCommand
import spring.oauth.tutorial.auth.domain.Account

interface SignUpUseCase {

    fun signUp(command: SignUpCommand): Account
}
