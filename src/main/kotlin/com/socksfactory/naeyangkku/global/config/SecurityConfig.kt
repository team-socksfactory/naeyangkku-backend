package com.socksfactory.naeyangkku.global.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.socksfactory.naeyangkku.global.auth.jwt.JwtAuthenticationFilter
import com.socksfactory.naeyangkku.global.auth.jwt.JwtUtils
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig (
    private val jwtUtils: JwtUtils,
    private val objectMapper: ObjectMapper
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .cors {
                corsConfigurationSource()
            }

            .csrf {
                it.disable()
            }

            .formLogin {
                it.disable()
            }

            .securityContext {
                it.requireExplicitSave(true)
            }

            .sessionManagement { session ->
                session.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
                )
            }

            .authorizeHttpRequests {
                it
                    .requestMatchers("/user/**").permitAll()
                    .requestMatchers("/share/**").permitAll()
                    .requestMatchers("/auth/**").permitAll()
                    .anyRequest().authenticated()
            }

            .addFilterBefore(JwtAuthenticationFilter(jwtUtils, objectMapper), UsernamePasswordAuthenticationFilter::class.java)

            .exceptionHandling {
                it.authenticationEntryPoint(HttpStatusEntryPoint(HttpStatus.NOT_FOUND))
                it.authenticationEntryPoint(HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
            }

            .build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val corsConfiguration = CorsConfiguration()
        corsConfiguration.addAllowedOriginPattern("*")
        corsConfiguration.addAllowedHeader("*")
        corsConfiguration.addAllowedMethod("*")
        corsConfiguration.allowCredentials = true

        val urlBasedCorsConfigurationSource = UrlBasedCorsConfigurationSource()
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration)

        return urlBasedCorsConfigurationSource
    }

}
