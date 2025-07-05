package party.cattery.lulu.service

import org.springframework.stereotype.Service

@Service
class InvestingService {
    fun rollDX(
        wager: Long,
        guess: Int,
        sides: Int,
    ): Boolean {
        val winningNum = (1..sides).random()
        return winningNum == guess
    }
}
