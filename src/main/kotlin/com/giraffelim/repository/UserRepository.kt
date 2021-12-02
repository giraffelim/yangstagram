package com.giraffelim.repository

import com.giraffelim.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    fun findByEmailOrUsername(email: String, username: String): User?
}