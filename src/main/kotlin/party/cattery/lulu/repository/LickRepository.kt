package party.cattery.lulu.repository

import party.cattery.lulu.entity.LickEntity

import org.springframework.data.jpa.repository.JpaRepository

interface LickRepository : JpaRepository<LickEntity, Long> {
    fun findByUserId(userId: String): LickEntity?
}
