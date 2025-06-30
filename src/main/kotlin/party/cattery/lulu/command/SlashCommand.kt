package party.cattery.lulu.command

import dev.kord.core.behavior.interaction.response.DeferredPublicMessageInteractionResponseBehavior
import dev.kord.core.event.interaction.ChatInputCommandInteractionCreateEvent
import dev.kord.rest.builder.interaction.GlobalChatInputCreateBuilder

abstract class SlashCommand {
    abstract val name: String
    abstract val description: String

    open fun GlobalChatInputCreateBuilder.buildDefinition() { }

    suspend fun execute(event: ChatInputCommandInteractionCreateEvent) {
        val ack = event.interaction.deferPublicResponse()
        handle(event, ack)
    }

    protected abstract suspend fun handle(
        event: ChatInputCommandInteractionCreateEvent,
        ack: DeferredPublicMessageInteractionResponseBehavior,
    )
}
