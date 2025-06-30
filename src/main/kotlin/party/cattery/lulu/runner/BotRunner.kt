package party.cattery.lulu.runner

import party.cattery.lulu.command.CommandRegistrar

import dev.kord.core.Kord

import org.springframework.stereotype.Component

@Component
class BotRunner(
    private val commandRegistrar: CommandRegistrar,
) {

    suspend fun run(kord: Kord) {
        commandRegistrar.registerAll(kord)

        kord.login()
    }
}
