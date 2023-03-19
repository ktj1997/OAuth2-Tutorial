package spring.oauth.tutorial.auth.adapter.inbound.rest.model

import spring.oauth.tutorial.auth.applicaiton.inbound.model.OAuthSignInQuery
import spring.oauth.tutorial.auth.applicaiton.inbound.model.OAuthSignInResult
import spring.oauth.tutorial.auth.domain.OAuthType

data class OAuthSignInRequest(
    val authorizationCode:String,
    val provider: String
){

    fun toQuery() : OAuthSignInQuery{
        try{
            return OAuthSignInQuery(
                provider = OAuthType.valueOf(provider),
                authorizationCode = authorizationCode
            )
        }catch (e: IllegalArgumentException){
            throw IllegalArgumentException("$provider is not supported oAuthType")
        }
    }
}

data class OAuthSignInResponse(
    val token:String
){
    companion object{
        fun fromResult(result: OAuthSignInResult): OAuthSignInResponse{
            return OAuthSignInResponse(
                token = result.token
            )
        }
    }
}