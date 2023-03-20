package spring.oauth.tutorial.auth.domain

data class Account(
    val email:String,
    val userName:String?,
    val password:String?,
    val oAuthType: OAuthType
)