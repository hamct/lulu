package party.cattery.lulu.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "discord")
class DiscordProperties {
    lateinit var token: String
}
