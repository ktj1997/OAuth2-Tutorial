package spring.oauth.tutorial.auth.adapter.outbound.persistence.jpa.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import spring.oauth.tutorial.auth.domain.Account
import spring.oauth.tutorial.auth.domain.OAuthType

@Entity
data class AccountEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val userName: String?,
    val password: String?,

    @Column(unique = true)
    val email: String,

    @Column
    val userIdentifier:String,

    @Column
    @Enumerated(EnumType.STRING)
    val oauthType: OAuthType
) {
    companion object {
        fun fromDomain(domain: Account): AccountEntity {
            return AccountEntity(
                userName = domain.userName,
                password = domain.password,
                email = domain.email,
                oauthType = domain.oAuthType,
                userIdentifier = domain.userIdentifier
            )
        }
    }
}
