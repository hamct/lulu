package party.cattery.lulu.command.base

import party.cattery.lulu.command.CommandScope
import party.cattery.lulu.command.ResponseVisibility

import dev.kord.core.Kord
import dev.kord.core.event.interaction.ApplicationCommandInteractionCreateEvent

sealed class BaseCommand {
    abstract val name: String
    abstract val description: String
    abstract val scopes: Set<CommandScope>
    abstract val visibility: ResponseVisibility

    abstract suspend fun register(kord: Kord)

    abstract suspend fun execute(event: ApplicationCommandInteractionCreateEvent)
}
