package com.giraffelim.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/auth")
class AuthController {

    @GetMapping("/login")
    fun loginForm() = "/auth/loginForm"

    @GetMapping("/login/error")
    fun loginError(model: Model): String {
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요")
        return "auth/loginForm"
    }

}