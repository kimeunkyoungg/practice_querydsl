package com.back.domain.member.member.repository

import com.back.domain.member.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long>, MemberRepositoryCustom {

    fun findByUsername(username: String): Member?
    fun findByApiKey(apiKey: String): Member?
    fun findByIdIn(ids: List<Long>): List<Member>
    fun findByUsernameAndNickname(username: String, nickname: String): Member?


}
