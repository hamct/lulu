package party.cattery.lulu.command

import dev.kord.core.event.interaction.ChatInputCommandInteractionCreateEvent
import dev.kord.rest.builder.interaction.GlobalChatInputCreateBuilder

interface SlashCommand {
    val name: String
    val description: String

    fun GlobalChatInputCreateBuilder.buildDefinition() { }

    suspend fun execute(event: ChatInputCommandInteractionCreateEvent) { }
}
