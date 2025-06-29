package party.cattery.lulu.runner

import party.cattery.lulu.command.CommandRegistrar
import party.cattery.lulu.config.DiscordProperties

import kotlinx.coroutines.runBlocking

import dev.kord.core.Kord

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class BotRunner(
    val props: DiscordProperties,
    val commandRegistrar: CommandRegistrar,
) : ApplicationRunner {

    override fun run(args: ApplicationArguments) {
        runBlocking {
            val kord = Kord(props.token)
            commandRegistrar.registerAll(kord)

            kord.login()
        }
    }
}
