package spring.oauth.tutorial.auth.adapter.outbound.persistence

import org.springframework.stereotype.Component
import spring.oauth.tutorial.auth.adapter.outbound.persistence.jpa.repository.AccountJpaRepository
import spring.oauth.tutorial.auth.adapter.outbound.persistence.mapper.toDomain
import spring.oauth.tutorial.auth.applicaiton.outbound.persistence.GetAccountPort
import spring.oauth.tutorial.auth.domain.Account
import spring.oauth.tutorial.auth.domain.OAuthType

@Component
class GetAccountAdapter(
    private val accountJpaRepository: AccountJpaRepository
) : GetAccountPort {
    override fun findAccountByEmailAndOAuthType(email: String, oAuthType: OAuthType): Account? {
        val entity = accountJpaRepository.findByEmailAndOauthType(email, oAuthType)
        return entity?.toDomain()
    }
}
