import com.fasterxml.jackson.annotation.JsonProperty

data class OAuthTokenResponse(
    val token: String
)
data class KaKaoOauthClientResponse(
    @JsonProperty(value = "token_type")
    val tokenType: String,
    @JsonProperty(value = "access_token")
    val accessToken: String,
    @JsonProperty(value = "expires_in")
    val expiresIn: Long,
    @JsonProperty(value = "refresh_token")
    val refreshToken: String,
    @JsonProperty(value = "refresh_token_expires_in")
    val refreshTokenExpiresIn: Long,
)

data class GoogleOauthClientRequest(
    val token: String
)
data class GoogleOauthClientResponse(
    val token: String
)
