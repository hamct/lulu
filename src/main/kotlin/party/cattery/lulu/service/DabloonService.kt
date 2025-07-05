package party.cattery.lulu.service

import party.cattery.lulu.entity.DabloonAccountEntity
import party.cattery.lulu.repository.DabloonAccountRepository

import dev.kord.common.entity.Snowflake

import org.springframework.stereotype.Service

import jakarta.transaction.Transactional

@Service
class DabloonService(
    private val repo: DabloonAccountRepository,
) {

    @Transactional
    fun getBalance(userId: Snowflake): Long {
        val account = repo.findByUserId(userId.toString()) ?: DabloonAccountEntity(userId = userId.toString(), 1000)
        repo.save(account)
        return account.balance
    }

    fun setBalance(
        userId: Snowflake,
        balance: Long,
    ) {
        repo.save(DabloonAccountEntity(userId.toString(), balance))
    }

    @Transactional
    fun addToBalance(
        userId: Snowflake,
        amount: Long,
    ) {
        val account = repo.findByUserId(userId.toString()) ?: DabloonAccountEntity(userId.toString())
        account.balance += amount
        repo.save(account)
    }

    @Transactional
    fun subtractFromBalance(
        userId: Snowflake,
        amount: Long,
    ): Long {
        val account = repo.findByUserId(userId.toString()) ?: DabloonAccountEntity(userId.toString())
        if (account.balance < amount) return -1
        account.balance -= amount
        repo.save(account)
        return account.balance
    }

    @Transactional
    fun transferBalance(
        userId: Snowflake,
        recipient: Snowflake,
        amount: Long,
    ): Boolean {
        if (amount <= 0) return false

        val from = repo.findByUserId(userId.toString()) ?: DabloonAccountEntity(userId.toString())
        val to = repo.findByUserId(recipient.toString()) ?: DabloonAccountEntity(recipient.toString())

        if (from.balance < amount) return false

        from.balance -= amount
        to.balance += amount

        repo.save(from)
        repo.save(to)

        return true
    }
}
