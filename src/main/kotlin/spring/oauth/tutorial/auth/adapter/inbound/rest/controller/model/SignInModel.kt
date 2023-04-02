import spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller.model.SignInUseCaseInput
import spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller.model.SignInUseCaseOutput
import spring.oauth.tutorial.auth.domain.OAuthType

data class SignInRequest(
    val userName: String,
    val password: String,
    val provider: OAuthType
) {
    fun toQuery(): SignInUseCaseInput {
        return SignInUseCaseInput(
            userName = this.userName,
            password = this.password
        )
    }
}

data class SignInResponse(
    val token: String
) {
    companion object {
        fun fromResult(result: SignInUseCaseOutput): SignInResponse {
            return SignInResponse(
                token = result.token
            )
        }
    }
}
