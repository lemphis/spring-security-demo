package me.jh.springsecuritydemo.account

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RestController

@RestController
class AccountController(
    private val accountService: AccountService
) {

    @GetMapping("/account/{role}/{username}/{password}")
    fun createAccount(@ModelAttribute account: Account): Account = accountService.createNew(account)

}