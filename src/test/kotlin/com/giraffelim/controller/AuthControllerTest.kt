package com.giraffelim.controller

import com.giraffelim.dto.UserFormDTO
import com.giraffelim.entity.User
import com.giraffelim.service.UserService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers
import org.springframework.test.web.servlet.MockMvc
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AuthControllerTest {

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    @BeforeEach
    fun createMember() {
        val userFormDTO = UserFormDTO(
            email = "test@tester.com",
            name = "gildong",
            username = "tester",
            password = "test12345"
        )
        val user = User.createUser(userFormDTO, passwordEncoder)
        userService.saveUser(user)
    }

    @Test
    @DisplayName("로그인 성공 테스트")
    fun `login_success_test`() {
        val username = "tester"
        val password = "test12345"

        mockMvc
            .perform(
                formLogin()
                    .userParameter("username")
                    .loginProcessingUrl("/auth/login")
                    .user(username).password(password)
            )
            .andExpect {
                SecurityMockMvcResultMatchers.authenticated()
            }
    }

    @Test
    @DisplayName("로그인 실패 테스트")
    fun `login_fail_test`() {
        val username = "tester"
        val password = "1234"

        mockMvc
            .perform(
                formLogin()
                    .userParameter("username")
                    .loginProcessingUrl("/auth/login")
                    .user(username).password(password)
            )
            .andExpect {
                SecurityMockMvcResultMatchers.unauthenticated()
            }
    }

}