package spring.oauth.tutorial.auth.adapter.outbound.oauth.client.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class KakaoOauthUserInfoResponse(
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