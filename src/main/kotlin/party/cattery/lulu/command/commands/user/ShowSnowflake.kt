package party.cattery.lulu.command.commands.user

import party.cattery.lulu.annotation.Command
import party.cattery.lulu.command.CommandScope

import dev.kord.core.behavior.interaction.response.DeferredMessageInteractionResponseBehavior
import dev.kord.core.behavior.interaction.response.respond
import dev.kord.core.event.interaction.UserCommandInteractionCreateEvent

@Command
class ShowSnowflake : UserCommand() {
    override val name = "Show Snowflake"
    override val description = "Shows the user's snowflake."
    override val scopes = setOf(CommandScope.GLOBAL)

    override suspend fun handle(
        event: UserCommandInteractionCreateEvent,
        ack: DeferredMessageInteractionResponseBehavior,
    ) {
        ack.respond {
            content =
                event.interaction.user.id
                    .toString()
        }
    }
}
