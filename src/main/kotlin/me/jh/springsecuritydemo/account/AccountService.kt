package me.jh.springsecuritydemo.account

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AccountService(
    private val accountRepository: AccountRepository,
    private val passwordEncoder: PasswordEncoder
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val account = accountRepository.findByUsername(username) ?: throw UsernameNotFoundException(username)

        return User.builder()
            .username(account.username)
            .password(account.password)
            .roles(account.role)
            .build()
    }

    fun createNew(account: Account): Account {
        account.encodePassword(passwordEncoder)
        return accountRepository.save(account)
    }

}