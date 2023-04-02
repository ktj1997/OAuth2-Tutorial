package spring.oauth.tutorial.auth.applicaiton.inbound.rest.controller.model

data class SignInUseCaseInput(
    val userName: String,
    val password: String
)

data class SignInUseCaseOutput(val token: String)
