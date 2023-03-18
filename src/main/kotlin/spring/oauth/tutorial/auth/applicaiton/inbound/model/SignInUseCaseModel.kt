package spring.oauth.tutorial.auth.applicaiton.inbound.model

data class SignInQuery(
    val userName:String,
    val password:String
)

data class SignInResult(val token:String)
