package com.back.domain.member.member.repository

import com.back.domain.member.member.entity.Member
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable


//이름은 자유지만 관례적으로 Custom을 붙인다.
//쿼리 dsl은 여기에 추가
interface MemberRepositoryCustom {
    fun findQById(id: Long): Member?
    fun findQByUsername(string: String): Member?
    fun findQByIdIn(ids: List<Long>): List<Member>
    fun findQByUsernameAndNickname(username: String, nickname: String): Member?
    fun findQByUsernameAOrNickname(username: String, nickname: String): List<Member>
    fun findQByUsernameAndEitherPasswordOrNickname(username: String, password: String, nickname: String): List<Member>

    fun findQByNicknameContaining(nickname: String): List<Member>
    fun findQByNicknameContaining(nickname: String, pageable: Pageable): Page<Member>
    fun countQByNicknameContaining(nickname: String): Long
    fun existsQByNicknameContaining(nickname: String): Boolean

}