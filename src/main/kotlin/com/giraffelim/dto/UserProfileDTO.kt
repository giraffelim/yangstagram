package com.giraffelim.dto

import com.giraffelim.entity.User

data class UserProfileDTO(
    var username: String? = null,
    var profileImgUrl: String? = null
) {
    companion object {
        fun create(user: User) = UserProfileDTO(
            username = user.username,
            profileImgUrl = user.profileImageUrl
        )
    }
}