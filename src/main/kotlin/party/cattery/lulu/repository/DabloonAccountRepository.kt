package party.cattery.lulu.repository

import party.cattery.lulu.entity.DabloonAccountEntity

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock

import jakarta.persistence.LockModeType

interface DabloonAccountRepository : JpaRepository<DabloonAccountEntity, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    fun findByUserId(userId: String): DabloonAccountEntity?
}
