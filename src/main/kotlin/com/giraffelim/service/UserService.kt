package com.giraffelim.service

import com.giraffelim.entity.User
import com.giraffelim.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService(
    private val userRepository: UserRepository
) {

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

}