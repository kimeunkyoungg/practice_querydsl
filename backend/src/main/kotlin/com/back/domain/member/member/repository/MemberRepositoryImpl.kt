package com.back.domain.member.member.repository

import com.back.domain.member.member.entity.Member
import com.back.domain.member.member.entity.QMember
import com.querydsl.jpa.impl.JPAQueryFactory

//얘는 이름 정확하게 정해져있음
//Custom을 구현
class MemberRepositoryImpl(
    private val jpaQuery: JPAQueryFactory
) : MemberRepositoryCustom {

    override fun findQById(id: Long): Member? {
        val member = QMember.member

        return jpaQuery
            .selectFrom(member)
            .where(member.id.eq(id)) // where member.id = id
            .fetchOne() // limit 1
    }

    override fun findQByUsername(username: String): Member? {
        val member = QMember.member

        return jpaQuery
            .selectFrom(member)
            .where(member.username.eq(username))
            .fetchOne()
    }

    override fun findQByIdIn(ids: List<Long>): List<Member> {
        val member = QMember.member

        return jpaQuery
            .selectFrom(member)
            .where(member.id.`in`(ids)) //코틀린 키워드로 in을 하기때문에 백틱으로
            .fetch()
    }

    override fun findQByUsernameAndNickname(username: String, nickname: String): Member? {
        val member = QMember.member

        return jpaQuery
            .selectFrom(member)
            .where(
                member.username.eq(username)
                    .and(member.nickname.eq(nickname)))
            .fetchOne()
    }

    override fun findQByUsernameAOrNickname(username: String, nickname: String): List<Member>{
        val member = QMember.member

        return jpaQuery
            .selectFrom(member)
            .where(
                member.username.eq(username)
                    .or(member.nickname.eq(nickname)))
            .fetch()
    }

    override fun findQByUsernameAndEitherPasswordOrNickname(username: String, password: String, nickname: String): List<Member>{
        val member = QMember.member

        return jpaQuery
            .selectFrom(member)
            .where(
                member.username.eq(username)
                    .and(
                        (member.password.eq(password)
                        .or(member.nickname.eq(nickname)))
                    )
            )
            .fetch()
    }



}