package me.jh.springsecuritydemo.account

import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<Account, Long> {

    fun findByUsername(username: String): Account?

}