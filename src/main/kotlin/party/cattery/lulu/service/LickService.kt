package party.cattery.lulu.service

import party.cattery.lulu.LickRepository
import party.cattery.lulu.entity.LickEntity

import org.springframework.stereotype.Service

@Service
class LickService(
    private val repo: LickRepository,
) {

    fun getTimesLicked(userId: String): Int {
        val lick = repo.findByUserId(userId)
        return lick?.lickedCount ?: 0
    }

    fun increment(userId: String): Int {
        val lick = repo.findByUserId(userId) ?: LickEntity(userId = userId)
        lick.lickedCount += 1
        repo.save(lick)
        return lick.lickedCount
    }
}
