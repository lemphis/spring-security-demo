package me.jh.springsecuritydemo.form

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import java.security.Principal

@Controller
class SampleController {

    @GetMapping("/")
    fun index(model: Model, principal: Principal?): String {
        model.addAttribute("message", "Hello ${principal?.name ?: "Spring Security"}")
        return "index"
    }

    @GetMapping("/info")
    fun info(model: Model): String {
        model.addAttribute("message", "Info")
        return "index"
    }

    @GetMapping("/dashboard")
    fun dashboard(model: Model, principal: Principal): String {
        model.addAttribute("message", principal.name)
        return "index"
    }

    @GetMapping("/admin")
    fun admin(model: Model, principal: Principal): String {
        model.addAttribute("message", "Hello Admin, ${principal.name}")
        return "index"
    }

}