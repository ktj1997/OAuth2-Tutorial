package spring.oauth.tutorial.auth.adapter.outbound.persistence.mapper

import spring.oauth.tutorial.auth.adapter.outbound.persistence.jpa.entity.AccountEntity
import spring.oauth.tutorial.auth.domain.Account

fun AccountEntity.toDomain() : Account {
    return Account(
        email = this.email,
        userName = this.userName,
        password = this.password,
        oAuthType = this.oAuthType
    )
}