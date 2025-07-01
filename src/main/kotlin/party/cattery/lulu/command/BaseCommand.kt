package party.cattery.lulu.command

import dev.kord.core.Kord
import dev.kord.core.event.interaction.ApplicationCommandInteractionCreateEvent

sealed class BaseCommand {
    abstract val name: String
    abstract val description: String
    abstract val scopes: Set<CommandScope>

    abstract suspend fun register(kord: Kord)

    abstract suspend fun execute(event: ApplicationCommandInteractionCreateEvent)
}
