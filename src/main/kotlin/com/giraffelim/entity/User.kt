package com.giraffelim.entity

import com.giraffelim.annotation.AllOpen
import com.giraffelim.constant.Gender
import com.giraffelim.constant.Role
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
    var role: Role? = null
)