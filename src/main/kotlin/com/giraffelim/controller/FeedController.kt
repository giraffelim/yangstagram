package com.giraffelim.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import java.security.Principal

@Controller
class FeedController {

    @GetMapping("/")
    fun feed(model: Model, principal: Principal): String {
        model.addAttribute("loginUsername", principal.name)
        return "feed"
    }
}