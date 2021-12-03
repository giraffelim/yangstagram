package com.giraffelim.controller

import com.giraffelim.dto.UserFormDTO
import com.giraffelim.entity.User
import com.giraffelim.service.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.security.Principal
import javax.validation.Valid

@Controller
@RequestMapping("/users")
class UserController(
    private val userService: UserService,
    private val passwordEncoder: PasswordEncoder
) {

    @GetMapping("/new")
    fun usersForm(model: Model): String {
        model.addAttribute("userFormDTO", UserFormDTO())
        return "/user/userForm"
    }

    @PostMapping("/new")
    fun createUser(@Valid userFormDTO: UserFormDTO, bindingResult: BindingResult, model: Model): String {

        if (bindingResult.hasErrors()) {
            return "/user/userForm"
        }

        try {
            val joinUser = User.createUser(userFormDTO, passwordEncoder)
            userService.saveUser(joinUser)
        } catch(e: Exception) {
            model.addAttribute("errorMessage", e.message)
            return "/user/userForm"
        }

        return "redirect:/"
    }

    @GetMapping("/profile/{username}")
    fun profile(@PathVariable username: String, model: Model, principal: Principal): String {
        model.addAttribute("userInfo", userService.getUserProfile(username, principal.name))
        return "user/profile"
    }

}