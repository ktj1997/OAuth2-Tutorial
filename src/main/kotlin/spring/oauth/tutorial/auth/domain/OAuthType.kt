package spring.oauth.tutorial.auth.domain

enum class OAuthType(val registrationName: String) {
    NONE("none"), KAKAO("kakao"), GOOGLE("google")
}
