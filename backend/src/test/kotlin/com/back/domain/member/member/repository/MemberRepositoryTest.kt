package com.back.domain.member.member.repository

import com.back.standard.extentions.getOrThrow
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
class MemberRepositoryTest {

    @Autowired
    private lateinit var memberRepository: MemberRepository

    @Test
    fun `findById()`(){
        val member = memberRepository.findById(1).get()

        assertThat(member.id).isEqualTo(1)
    }

    @Test
    fun `findQById()`() {
        val member = memberRepository.findQById(1).getOrThrow()
        assertThat(member.id).isEqualTo(1)
    }

    @Test
    fun `findByUsername()`() {
        val member = memberRepository.findByUsername("user1").getOrThrow()
        assertThat(member.username).isEqualTo("user1")
    }

    @Test
    fun `findQByUsername()`() {
        val member = memberRepository.findQByUsername("user1").getOrThrow()
        assertThat(member.username).isEqualTo("user1")
    }

    @Test
    fun `findByIdIn()`() {
        val memberList = memberRepository.findByIdIn(listOf(1,2,3))
        assertThat(memberList.map { it.id }).containsAnyOf(1,2,3)
    }

    @Test
    fun `findQByIdIn()`() {
        val memberList = memberRepository.findQByIdIn(listOf(1,2,3))
        assertThat(memberList.map { it.id }).containsAnyOf(1,2,3)
    }

    @Test
    fun `findByUsernameAndNickname()`() {
        val member = memberRepository.findByUsernameAndNickname("user1", "유저1").getOrThrow()
        assertThat(member.username).isEqualTo("user1")
        assertThat(member.nickname).isEqualTo("유저1")
    }

    @Test
    fun `findQByUsernameAndNickname()`() {
        val member = memberRepository.findQByUsernameAndNickname("user1", "유저1").getOrThrow()
        assertThat(member.username).isEqualTo("user1")
        assertThat(member.nickname).isEqualTo("유저1")
    }
}