package spring.oauth.tutorial.auth.adapter.outbound.oauth

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.stereotype.Component
import spring.oauth.tutorial.auth.adapter.outbound.oauth.client.GoogleOAuthClient
import spring.oauth.tutorial.auth.adapter.outbound.oauth.client.KakaoOAuthClient
import spring.oauth.tutorial.auth.adapter.outbound.oauth.client.OAuthClient
import spring.oauth.tutorial.auth.domain.OAuthType

@Component
class OAuthClientMap(
    objectMapper: ObjectMapper,
    registrationRepository: ClientRegistrationRepository
) {
    private lateinit var clientMap: Map<OAuthType, OAuthClient>
    init {
        clientMap = mapOf(
            OAuthType.GOOGLE to GoogleOAuthClient(objectMapper, registrationRepository.findByRegistrationId(OAuthType.GOOGLE.registrationName)),
            OAuthType.KAKAO to KakaoOAuthClient(registrationRepository.findByRegistrationId(OAuthType.KAKAO.registrationName))
        )
    }

    fun findByProvider(type: OAuthType): OAuthClient {
        return clientMap[type] ?: throw IllegalArgumentException("OAuth Client Not Found $type")
    }
}
