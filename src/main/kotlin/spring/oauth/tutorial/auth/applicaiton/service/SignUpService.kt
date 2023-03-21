package spring.oauth.tutorial.auth.applicaiton.service

import org.springframework.stereotype.Service
import spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller.SignUpUseCase
import spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller.model.SignUpCommand
import spring.oauth.tutorial.auth.applicaiton.outbound.persistence.SaveAccountPort
import spring.oauth.tutorial.auth.domain.Account

@Service
class SignUpService(
    private val saveAccountPort: SaveAccountPort
) : SignUpUseCase {
    override fun signUp(command: SignUpCommand): Account {
        return saveAccountPort.save(command.toDomain())
    }
}
