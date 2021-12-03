package com.giraffelim.service

import com.giraffelim.dto.UserProfileDTO
import com.giraffelim.entity.User
import com.giraffelim.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService(
    private val userRepository: UserRepository
): UserDetailsService {

    fun saveUser(user: User): User {
        validateDuplicateUser(user)
        return userRepository.save(user)
    }

    fun validateDuplicateUser(user: User) {
        val findUser = userRepository.findByEmailOrUsername(user.email!!, user.username!!)
        if (findUser != null) {
            throw IllegalStateException("이미 가입된 회원입니다.")
        }
    }

    fun getUserProfile(username: String, loginUsername: String): UserProfileDTO {
        val userEntity = userRepository.findByUsername(username) ?: throw UsernameNotFoundException(username)
        return UserProfileDTO.create(userEntity)
    }

    override fun loadUserByUsername(username: String): UserDetails {
        val findUser = userRepository.findByUsername(username) ?: throw UsernameNotFoundException(username)
        return org.springframework.security.core.userdetails.User.builder()
            .username(username)
            .password(findUser.password)
            .roles(findUser.role.toString())
            .build()
    }

}