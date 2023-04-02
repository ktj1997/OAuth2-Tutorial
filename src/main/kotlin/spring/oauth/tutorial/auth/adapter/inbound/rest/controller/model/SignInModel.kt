import spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller.model.SignInQuery
import spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller.model.SignInResult
import spring.oauth.tutorial.auth.domain.OAuthType

data class SignInRequest(
    val userName: String,
    val password: String,
    val provider: OAuthType
) {
    fun toQuery(): SignInQuery {
        return SignInQuery(
            userName = this.userName,
            password = this.password
        )
    }
}

data class SignInResponse(
    val token: String
) {
    companion object {
        fun fromResult(result: SignInResult): SignInResponse {
            return SignInResponse(
                token = result.token
            )
        }
    }
}
