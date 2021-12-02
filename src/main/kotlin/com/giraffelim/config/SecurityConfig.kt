package com.giraffelim.config

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        http {
            csrf { disable() }
            authorizeRequests {
                authorize("/auth/**", permitAll)
                authorize("/users/**", permitAll)
                authorize("/**", authenticated)
            }
            formLogin {
                loginPage = "/auth/login"
                defaultSuccessUrl("/", true)
            }
            logout {
                logoutUrl = "/auth/logout"
                logoutSuccessUrl = "/auth/login"
            }
        }
    }

    override fun configure(web: WebSecurity?) {
        web!!.ignoring().antMatchers("/css/**", "/images/**")
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

}