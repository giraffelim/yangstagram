package com.giraffelim.entity

import com.giraffelim.annotation.AllOpen
import com.giraffelim.constant.Gender
import com.giraffelim.constant.Role
import com.giraffelim.dto.UserFormDTO
import com.giraffelim.dto.UserProfileDTO
import com.giraffelim.entity.audit.BaseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import javax.persistence.*

@AllOpen
@Entity
@Table(name = "USER")
class User(
    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    var id: Long? = null,

    @Column(unique = true)
    var username: String? = null,

    var password: String? = null,

    var name: String? = null,

    @Lob
    var bio: String? = null,

    var email: String? = null,

    var phone: String? = null,

    @Enumerated(EnumType.STRING)
    var gender: Gender? = null,

    var profileImageUrl: String? = null,

    @Enumerated(EnumType.STRING)
    var role: Role? = null,

    var website: String? = null
): BaseEntity() {
    companion object {
        fun createUser(userFormDTO: UserFormDTO, passwordEncoder: PasswordEncoder) = User(
            username = userFormDTO.username,
            password = passwordEncoder.encode(userFormDTO.password),
            name = userFormDTO.name,
            email = userFormDTO.email,
            role = Role.USER
        )
    }

    fun edit(userProfileDTO: UserProfileDTO) {
        this.website = userProfileDTO.website
        this.bio = userProfileDTO.bio
        this.phone = userProfileDTO.phone
        this.gender = userProfileDTO.gender?.let {
            Gender.valueOf(it)
        }
    }

}