package party.cattery.lulu.command

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

    protected abstract suspend fun handle(
        event: MessageCommandInteractionCreateEvent,
        ack: DeferredMessageInteractionResponseBehavior,
    )

    override suspend fun execute(event: ApplicationCommandInteractionCreateEvent) {
        handle(event as MessageCommandInteractionCreateEvent, event.interaction.deferEphemeralResponse())
    }
}
