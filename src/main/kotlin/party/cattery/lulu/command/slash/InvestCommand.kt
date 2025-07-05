package party.cattery.lulu.command.slash

import party.cattery.lulu.annotation.Command
import party.cattery.lulu.command.CommandScope
import party.cattery.lulu.command.ResponseVisibility
import party.cattery.lulu.command.base.SlashCommand
import party.cattery.lulu.service.DabloonService
import party.cattery.lulu.service.InvestingService

import dev.kord.core.behavior.interaction.response.DeferredMessageInteractionResponseBehavior
import dev.kord.core.behavior.interaction.response.respond
import dev.kord.core.event.interaction.ChatInputCommandInteractionCreateEvent
import dev.kord.rest.builder.interaction.ChatInputCreateBuilder
import dev.kord.rest.builder.interaction.integer

@Command
class InvestCommand(
    private val dabloonService: DabloonService,
    private val investingService: InvestingService,
) : SlashCommand() {

    companion object {
    }

    override val name = "invest"
    override val description = "Get some easy dablooons"
    override val scopes = setOf(CommandScope.GLOBAL)
    override val visibility = ResponseVisibility.PUBLIC

    override fun ChatInputCreateBuilder.buildDefinition() {
        integer(name = "wager", description = "How much you want to invest") {
            required = true
        }
        integer(name = "guess", description = "You are always a winner!") {
            required = true
            (1L..6L).map {
                choice("$it", it)
            }
        }
    }

    override suspend fun handle(
        event: ChatInputCommandInteractionCreateEvent,
        ack: DeferredMessageInteractionResponseBehavior,
    ) {
        val balance = dabloonService.getBalance(event.interaction.user.id)
        val wager = event.interaction.command.integers["wager"]!!
        val guess = event.interaction.command.integers["guess"]!!
        if (wager > balance) {
            ack.respond { content = "You don't have enough dabloons u brokie! (Current balance: $balance†)" }
        }

        val outcome = investingService.rollDX(wager, guess.toInt(), 6)
        val response =
            if (outcome) {
                dabloonService.addToBalance(event.interaction.user.id, wager)
                "What a great investor! (New balance: ${balance + wager}†)"
            } else {
                dabloonService.subtractFromBalance(event.interaction.user.id, wager)
                "You should try again, your investment will surely grow next time (New balance: ${balance - wager}†)"
            }

        ack.respond {
            content = response
        }
    }
}
