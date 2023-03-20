package spring.oauth.tutorial.auth.applicaiton.inbound.model

import spring.oauth.tutorial.auth.domain.Account
import spring.oauth.tutorial.auth.domain.OAuthType

data class SignUpCommand(
    val userName: String?,
    val password: String?,
    val oAuthType: OAuthType,
    val email: String,
) {
    fun toDomain(): Account {
        return Account(
            userName = this.userName,
            password = this.password,
            oAuthType = this.oAuthType,
            email = this.email
        )
    }
}
