package party.cattery.lulu

import party.cattery.lulu.config.DiscordProperties

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties(DiscordProperties::class)
@SpringBootApplication(scanBasePackages = ["party.cattery.lulu"])
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
