package spring.oauth.tutorial.auth.adapter.outbound.rest

import org.springframework.stereotype.Component
import spring.oauth.tutorial.auth.adapter.outbound.rest.client.OAuthClient
import spring.oauth.tutorial.auth.domain.OAuthType

@Component
class OAuthClientFactory(
    clients: Set<OAuthClient>
) {
    private lateinit var clientMap: Map<OAuthType,OAuthClient>
    init {
        clientMap = clients
        .associateBy { provider -> provider.getProvider() }
    }

    fun findByProvider(type:OAuthType) : OAuthClient{
        return clientMap[type] ?: throw IllegalArgumentException("OAuth Client Not Found $type")
    }
}