package com.giraffelim.dto

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

data class UserFormDTO(
    @field:NotEmpty(message = "이메일은 필수 값입니다.")
    @field:Email(message = "이메일 형식으로 입력해주세요.")
    var email: String? = null,

    @field:NotBlank(message = "이름은 필수 입력 값입니다.")
    var name: String? = null,

    @field:NotBlank(message = "유저 이름은 필수 입력 값입니다.")
    var username: String? = null,

    @field:NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @field:Length(min = 8, max = 16, message = "비밀번호는 8자 이상 16자 이하로 입력해주세요.")
    var password: String? = null,
)
