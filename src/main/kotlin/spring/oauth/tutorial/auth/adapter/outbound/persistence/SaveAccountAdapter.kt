package spring.oauth.tutorial.auth.adapter.outbound.persistence

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import spring.oauth.tutorial.auth.adapter.outbound.persistence.jpa.entity.AccountEntity
import spring.oauth.tutorial.auth.adapter.outbound.persistence.jpa.repository.AccountJpaRepository
import spring.oauth.tutorial.auth.adapter.outbound.persistence.mapper.toDomain
import spring.oauth.tutorial.auth.applicaiton.outbound.persistence.SaveAccountPort
import spring.oauth.tutorial.auth.domain.Account

@Component
class SaveAccountAdapter(
    private val accountJpaRepository: AccountJpaRepository
) : SaveAccountPort {
    @Transactional
    override fun save(account: Account): Account {
        val entity = AccountEntity.fromDomain(account)
        return accountJpaRepository.save(entity).toDomain()
    }
}