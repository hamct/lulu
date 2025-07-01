package party.cattery.lulu.command.commands.slash

import party.cattery.lulu.command.BaseCommand

import dev.kord.core.Kord
import dev.kord.core.behavior.interaction.response.DeferredMessageInteractionResponseBehavior
import dev.kord.core.event.interaction.ApplicationCommandInteractionCreateEvent
import dev.kord.core.event.interaction.ChatInputCommandInteractionCreateEvent
import dev.kord.rest.builder.interaction.ChatInputCreateBuilder

abstract class SlashCommand : BaseCommand() {

    override suspend fun register(kord: Kord) {
        kord.createGlobalChatInputCommand(name, description) {
            buildDefinition()
        }
    }

    protected abstract suspend fun handle(
        event: ChatInputCommandInteractionCreateEvent,
        ack: DeferredMessageInteractionResponseBehavior,
    )

    override suspend fun execute(event: ApplicationCommandInteractionCreateEvent) {
        handle(event as ChatInputCommandInteractionCreateEvent, event.interaction.deferPublicResponse())
    }

    open fun ChatInputCreateBuilder.buildDefinition() { }
}
