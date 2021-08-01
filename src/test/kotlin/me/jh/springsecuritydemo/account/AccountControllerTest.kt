package me.jh.springsecuritydemo.account

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.context.support.WithAnonymousUser
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.transaction.annotation.Transactional

@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureMockMvc
internal class AccountControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var accountService: AccountService

    @Test
    @WithAnonymousUser
    fun `index - 익명 사용자가 접근할 때`() {
        mockMvc.perform(get("/"))
            .andDo(print())
            .andExpect(status().isOk)
    }

    @Test
    @WithUser
    fun `index - USER 권한을 가진 사용자가 접근할 때`() {
        mockMvc.perform(get("/"))
            .andDo(print())
            .andExpect(status().isOk)
    }

    @Test
    @WithUser
    fun `admin - USER 권한을 가진 사용자가 접근할 때`() {
        mockMvc.perform(get("/admin"))
            .andDo(print())
            .andExpect(status().isForbidden)
    }

    @Test
    @WithAdmin
    fun `admin - ADMIN 권한을 가진 사용자가 접근할 때`() {
        mockMvc.perform(get("/admin"))
            .andDo(print())
            .andExpect(status().isOk)
    }

    @Test
    @Transactional
    fun `login - 정상적인 계정으로 로그인`() {
        val username = "jh"
        val password = "123"
        val user = createUser(username = username, password = password)
        mockMvc.perform(formLogin().user(user.username).password(password))
            .andExpect(authenticated())
    }

    @Test
    @Transactional
    fun `login - 맞지 않는 패스워드로 로그인`() {
        val username = "jh"
        val password = "123"
        val user = createUser(username = username, password = password)
        mockMvc.perform(formLogin().user(user.username).password("12345"))
            .andExpect(unauthenticated())
    }

    private fun createUser(username: String, password: String): Account {
        val account = Account(username = username, password = password, role = "USER")
        return accountService.createNew(account)
    }

}