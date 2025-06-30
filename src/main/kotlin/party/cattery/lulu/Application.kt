package party.cattery.lulu

import party.cattery.lulu.config.DiscordProperties
import party.cattery.lulu.runner.BotRunner

import kotlinx.coroutines.runBlocking

import dev.kord.core.Kord

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties(DiscordProperties::class)
@SpringBootApplication(scanBasePackages = ["party.cattery.lulu"])
class Application

suspend fun main(args: Array<String>) {
    val context = runApplication<Application>(*args)

    val botRunner = context.getBean(BotRunner::class.java)
    val discordProperties = context.getBean(DiscordProperties::class.java)
    val kord = Kord(discordProperties.token)

    runBlocking {
        botRunner.run(kord)
    }
}
