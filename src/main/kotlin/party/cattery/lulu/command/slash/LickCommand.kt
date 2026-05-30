package party.cattery.lulu.command.slash

import party.cattery.lulu.annotation.Command
import party.cattery.lulu.command.CommandScope
import party.cattery.lulu.command.ResponseVisibility
import party.cattery.lulu.command.base.SlashCommand
import party.cattery.lulu.presentation.LickResponse
import party.cattery.lulu.service.LickService

import dev.kord.core.behavior.interaction.response.DeferredMessageInteractionResponseBehavior
import dev.kord.core.behavior.interaction.response.respond
import dev.kord.core.event.interaction.ChatInputCommandInteractionCreateEvent
import dev.kord.rest.builder.interaction.ChatInputCreateBuilder
import dev.kord.rest.builder.interaction.user

@Command
class LickCommand(
    private val lickService: LickService,
    private val lickResponse: LickResponse,
) : SlashCommand() {

    override val name = "lick"
    override val description = "Lick a user"
    override val scopes = setOf(CommandScope.GLOBAL)
    override val visibility = ResponseVisibility.PUBLIC

    override fun ChatInputCreateBuilder.buildDefinition() {
        user("lickee", "User to lick") {
            required = true
        }
    }

    override suspend fun handle(
        event: ChatInputCommandInteractionCreateEvent,
        ack: DeferredMessageInteractionResponseBehavior,
    ) {
        val interaction = event.interaction

        val command = interaction.command
        val lickedUser = command.users["lickee"]
        if (lickedUser == null) {
            return
        }

        lickService.increment(lickedUser.id.toString())
        val timesLicked = lickService.getTimesLicked(lickedUser.id.toString())

        ack.respond(lickResponse.buildResponse(interaction, lickedUser, timesLicked))
    }
}
