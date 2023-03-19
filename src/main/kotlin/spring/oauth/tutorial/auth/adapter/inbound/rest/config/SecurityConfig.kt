package spring.oauth.tutorial.auth.adapter.inbound.rest.config

import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientPropertiesRegistrationAdapter
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

@Configuration
@EnableWebSecurity
class SecurityConfig(
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
            .csrf().disable()

        http
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http
            .authorizeHttpRequests().requestMatchers(HttpMethod.POST,"/api/v1/auth/**").permitAll()
            .anyRequest()
            .hasAnyRole("USER")

        return http.build()
    }

    @Bean
    fun clientRegistrationRepository(
       oAuth2ClientProperties: OAuth2ClientProperties
    ) : ClientRegistrationRepository{
        val clientRegistrations =
            ArrayList(OAuth2ClientPropertiesRegistrationAdapter.getClientRegistrations(oAuth2ClientProperties).values)
        return InMemoryClientRegistrationRepository(clientRegistrations)
    }
}