package spring.oauth.tutorial.auth.adapter.outbound.persistence.jpa.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import spring.oauth.tutorial.auth.adapter.outbound.persistence.jpa.entity.AccountEntity
import spring.oauth.tutorial.auth.domain.OAuthType

@Repository
interface AccountJpaRepository : JpaRepository<AccountEntity, Long> {
    fun findByEmailAndOauthType(email: String, oAuthType: OAuthType): AccountEntity?
}
