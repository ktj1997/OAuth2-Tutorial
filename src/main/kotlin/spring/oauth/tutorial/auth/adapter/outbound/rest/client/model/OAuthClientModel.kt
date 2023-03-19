import com.fasterxml.jackson.annotation.JsonProperty

data class OAuthTokenResponse(
    val token:String
)

data class KaKaoOauthClientRequest(
    @JsonProperty(value = "code")
    val code:String,
    @JsonProperty(value = "client_id")
    val clientId:String,
    @JsonProperty(value = "client_secret")
    val clientSecret: String,
    @JsonProperty(value = "redirect_uri")
    val redirectUri: String,
    @JsonProperty(value = "grant_type")
    val grantType:String
)
data class KaKaoOauthClientResponse(
    @JsonProperty(value = "token_type")
    val tokenType:String,
    @JsonProperty(value = "access_token")
    val accessToken:String,
    @JsonProperty(value = "expires_in")
    val expiresIn: Long,
    @JsonProperty(value = "refresh_token")
    val refreshToken:String,
    @JsonProperty(value = "refresh_token_expires_in")
    val refreshTokenExpiresIn:Long,
)

data class GoogleOauthClientRequest(
    val token:String
)
data class GoogleOauthClientResponse(
    val token:String
)