package com.back.domain.member.member.repository

import com.back.domain.member.member.entity.Member


//이름은 자유지만 관례적으로 Custom을 붙인다.
//쿼리 dsl은 여기에 추가
interface MemberRepositoryCustom {
    fun findQById(id: Long): Member?
    fun findQByUsername(string: String): Member?
    fun findQByIdIn(ids: List<Long>): List<Member>
    fun findQByUsernameAndNickname(username: String, nickname: String): Member?
}