package party.cattery.lulu.command.commands.message

import party.cattery.lulu.command.BaseCommand
import party.cattery.lulu.command.CommandScope

import dev.kord.core.Kord
import dev.kord.core.behavior.interaction.response.DeferredMessageInteractionResponseBehavior
import dev.kord.core.event.interaction.ApplicationCommandInteractionCreateEvent
import dev.kord.core.event.interaction.MessageCommandInteractionCreateEvent

abstract class MessageCommand : BaseCommand() {

    override suspend fun register(kord: Kord) {
        if (scopes.contains(CommandScope.GLOBAL)) {
            kord.createGlobalMessageCommand(name)
        }
    }

    override fun matches(event: ApplicationCommandInteractionCreateEvent): Boolean =
        event is MessageCommandInteractionCreateEvent && event.interaction.invokedCommandName == name

    override suspend fun execute(event: ApplicationCommandInteractionCreateEvent) {
        handle(event as MessageCommandInteractionCreateEvent, event.interaction.deferEphemeralResponse())
    }

    protected abstract suspend fun handle(
        event: MessageCommandInteractionCreateEvent,
        ack: DeferredMessageInteractionResponseBehavior,
    )
}
