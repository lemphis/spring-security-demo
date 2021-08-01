package me.jh.springsecuritydemo.account

import org.springframework.security.test.context.support.WithMockUser

@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@WithMockUser(username = "jh", roles = ["ADMIN"])
annotation class WithAdmin
