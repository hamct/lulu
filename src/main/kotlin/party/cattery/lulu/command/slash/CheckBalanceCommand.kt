package party.cattery.lulu.command.slash

import party.cattery.lulu.annotation.Command
import party.cattery.lulu.command.CommandScope
import party.cattery.lulu.command.ResponseVisibility
import party.cattery.lulu.command.base.SlashCommand
import party.cattery.lulu.service.DabloonService

import dev.kord.core.behavior.interaction.response.DeferredMessageInteractionResponseBehavior
import dev.kord.core.behavior.interaction.response.respond
import dev.kord.core.event.interaction.ChatInputCommandInteractionCreateEvent

@Command
class CheckBalanceCommand(
    private val service: DabloonService,
) : SlashCommand() {
    override val name = "balance"
    override val description = "Check your balance"
    override val scopes = setOf(CommandScope.GLOBAL)
    override val visibility = ResponseVisibility.EPHEMERAL

    override suspend fun handle(
        event: ChatInputCommandInteractionCreateEvent,
        ack: DeferredMessageInteractionResponseBehavior,
    ) {
        val balance = service.getBalance(event.interaction.user.id)
        ack.respond {
            content = "Current Balance: $balance†"
        }
    }
}
