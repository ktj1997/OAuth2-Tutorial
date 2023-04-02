package spring.oauth.tutorial.auth.adapter.outbound.oauth.client.model

import com.fasterxml.jackson.annotation.JsonProperty
data class KaKaoOAuthClientResponse(
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
data class GoogleOAuthClientResponse(
    @JsonProperty(value = "token_type")
    val tokenType: String,
    @JsonProperty(value = "access_token")
    val accessToken: String,
    @JsonProperty(value = "expires_in")
    val expiresIn: Long,
    @JsonProperty(value = "refresh_token")
    val refreshToken: String,
    @JsonProperty(value = "id_token")
    val idToken: String,
    @JsonProperty(value = "scope")
    val scope: String
)
