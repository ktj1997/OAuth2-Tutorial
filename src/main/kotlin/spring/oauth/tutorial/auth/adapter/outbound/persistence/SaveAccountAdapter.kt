package spring.oauth.tutorial.auth.adapter.outbound.persistence

import java.util.UUID
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import spring.oauth.tutorial.auth.adapter.outbound.persistence.jpa.entity.AccountEntity
import spring.oauth.tutorial.auth.adapter.outbound.persistence.jpa.repository.AccountJpaRepository
import spring.oauth.tutorial.auth.adapter.outbound.persistence.mapper.toDomain
import spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller.model.SignUpUseCaseInput
import spring.oauth.tutorial.auth.applicaiton.outbound.persistence.SaveAccountPort
import spring.oauth.tutorial.auth.domain.Account

@Component
class SaveAccountAdapter(
    private val accountJpaRepository: AccountJpaRepository
) : SaveAccountPort {
    @Transactional
    override fun save(command: SignUpUseCaseInput): Account {

        val entity = AccountEntity(
            userName = command.userName,
            password = command.password,
            email = command.email,
            userIdentifier = UUID.randomUUID().toString(),
            oauthType = command.oAuthType
        )

        return accountJpaRepository.save(entity).toDomain()
    }
}
