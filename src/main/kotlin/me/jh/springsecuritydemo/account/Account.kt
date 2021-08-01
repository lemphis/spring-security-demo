package me.jh.springsecuritydemo.account

import org.springframework.security.crypto.password.PasswordEncoder
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Account(
    @Id @GeneratedValue
    var id: Long? = null,
    @Column(unique = true)
    var username: String?,
    var password: String?,
    var role: String?
) {

    constructor() : this(username = null, password = null, role = null)

    fun encodePassword(passwordEncoder: PasswordEncoder) {
        password = passwordEncoder.encode(password)
    }

}
