package com.back.domain.member.member.repository

import com.back.domain.member.member.entity.Member
import com.back.domain.member.member.entity.QMember
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.support.PageableExecutionUtils

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

    override fun findQByNicknameContaining(nickname: String): List<Member> {
        val member = QMember.member

        return jpaQuery
            .selectFrom(member)
            .where(
                member.nickname.contains(nickname)
            )
            .fetch()
    }

    override fun countQByNicknameContaining(nickname: String): Long {
        val member = QMember.member

        return jpaQuery
            .select(member.count())
            .from(member)
            .where(
                member.nickname.contains(nickname)
            )
            .fetchOne() ?: 0L
    }

    override fun existsQByNicknameContaining(nickname: String): Boolean{
        val member = QMember.member

        return jpaQuery
            .selectOne()
            .from(member)
            .where(member.nickname.contains(nickname))
            .fetchFirst()!=null


    }

    override fun findQByNicknameContaining(nickname: String, pageable: Pageable): Page<Member>{
        val member = QMember.member

        //content 쿼리
        val content = jpaQuery
            .selectFrom(member)
            .where(member.nickname.contains(nickname))
            .offset(pageable.offset) //시작
            .limit(pageable.pageSize.toLong()) //끝
            .fetch()
//
//        //totalCount 쿼리
//        val totalCount = jpaQuery
//            .select(member.count())
//            .from(member)
//            .where(member.nickname.contains(nickname))
//            .fetchOne() ?: 0L //null일때는 0으로 반환
//
//        return PageImpl(
//            content,
//            pageable,
//            totalCount
//        )
        return PageableExecutionUtils.getPage(content, pageable) {
            jpaQuery
                .select(member.count())
                .from(member)
                .where(member.nickname.contains(nickname))
                .fetchOne() ?: 0L
        }
    }



}