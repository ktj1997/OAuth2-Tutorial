package spring.oauth.tutorial.auth.adapter.outbound.oauth.client.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class KakaoOAuthUserInfoResponse(
    val id: Long,
    @JsonProperty("connected_at")
    val connectedAt: String,

    @JsonProperty("kakao_account")
    val kakaoAccount: KakaoAccount
) {
    data class KakaoAccount(
        val email: String,
        val isEmailValid: Boolean,
        val isEmailVerified: Boolean
    )
}

@JsonIgnoreProperties(ignoreUnknown = true)
data class GoogleOAuthUserInfoJwtResponse(
    @JsonProperty("email")
    val email: String,
    @JsonProperty("email_verified")
    val emailVerified: String,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("picture")
    val picture: String,
)

data class GoogleOAuthUserInfoResponse(
    @JsonProperty("id")
    val id: String,
    @JsonProperty("email")
    val email: String,
    @JsonProperty("verifed_email")
    val emailVerified: Boolean,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("picture")
    val picture: String,
)
