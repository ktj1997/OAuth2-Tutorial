package spring.oauth.tutorial.auth.adapter.inbound.rest.config

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository
import org.springframework.security.web.SecurityFilterChain
import spring.oauth.tutorial.auth.adapter.inbound.rest.config.properties.OAuth2Properties
import spring.oauth.tutorial.auth.adapter.outbound.oauth.OAuth2ProviderRepository

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(OAuth2Properties::class)
class SecurityConfig(
    private val oAuth2ProviderRepository: OAuth2ProviderRepository
){

    @Bean
    fun bcryptPasswordEncoder(): BCryptPasswordEncoder{
        return BCryptPasswordEncoder()
    }


    //refresh-token-rotation
    @Bean
    fun filterChain(http: HttpSecurity) : SecurityFilterChain{
        http
            .httpBasic().disable()
            .formLogin().disable()
            .logout().disable()

        http
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http
            .authorizeHttpRequests()
            .requestMatchers(HttpMethod.POST,"/api/v1/auth/signin")
            .permitAll()
            .anyRequest()
            .hasAnyRole("USER")

        http
            .oauth2Login()
            .clientRegistrationRepository(clientRegistrationRepository(oAuth2ProviderRepository))

        return http.build()
    }

    @Bean
    fun clientRegistrationRepository(
        oAuth2ProviderRepository: OAuth2ProviderRepository
    ) : ClientRegistrationRepository{
        return InMemoryClientRegistrationRepository(oAuth2ProviderRepository.getRegistrations())
    }
}