package com.giraffelim.dto

import com.giraffelim.entity.User

data class UserProfileDTO(
    var id: Long? = null,
    var name: String? = null,
    var website: String? = null,
    var bio: String? = null,
    var email: String? = null,
    var username: String? = null,
    var profileImgUrl: String? = null,
    var gender: String? = null,
    var phone: String? = null
) {
    companion object {
        fun create(user: User) = UserProfileDTO(
            id = user.id,
            name = user.name,
            username = user.username,
            profileImgUrl = user.profileImageUrl,
            bio = user.bio,
            email = user.email,
            gender = user.gender.toString(),
            website = user.website,
            phone = user.phone
        )
    }
}