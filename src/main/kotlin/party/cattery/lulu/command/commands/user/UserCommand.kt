package party.cattery.lulu.command.commands.user

import party.cattery.lulu.command.BaseCommand
import party.cattery.lulu.command.CommandScope

import dev.kord.core.Kord
import dev.kord.core.behavior.interaction.response.DeferredMessageInteractionResponseBehavior
import dev.kord.core.event.interaction.ApplicationCommandInteractionCreateEvent
import dev.kord.core.event.interaction.UserCommandInteractionCreateEvent

abstract class UserCommand : BaseCommand() {

    override suspend fun register(kord: Kord) {
        if (scopes.contains(CommandScope.GLOBAL)) {
            kord.createGlobalUserCommand(name)
        }
    }

    override fun matches(event: ApplicationCommandInteractionCreateEvent): Boolean =
        event is UserCommandInteractionCreateEvent && event.interaction.invokedCommandName == name

    override suspend fun execute(event: ApplicationCommandInteractionCreateEvent) {
        handle(event as UserCommandInteractionCreateEvent, event.interaction.deferEphemeralResponse())
    }

    protected abstract suspend fun handle(
        event: UserCommandInteractionCreateEvent,
        ack: DeferredMessageInteractionResponseBehavior,
    )
}
