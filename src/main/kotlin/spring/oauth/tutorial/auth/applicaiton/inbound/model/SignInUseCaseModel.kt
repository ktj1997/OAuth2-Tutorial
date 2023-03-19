package spring.oauth.tutorial.auth.applicaiton.inbound.model

import spring.oauth.tutorial.auth.domain.OAuthType

data class SignInQuery(
    val userName:String,
    val password:String
)

data class SignInResult(val token:String)