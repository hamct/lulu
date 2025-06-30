package party.cattery.lulu.command.commands

import party.cattery.lulu.annotation.Command
import party.cattery.lulu.command.SlashCommand
import party.cattery.lulu.response.LickResponse
import party.cattery.lulu.service.LickService

import dev.kord.core.behavior.interaction.response.DeferredPublicMessageInteractionResponseBehavior
import dev.kord.core.behavior.interaction.response.respond
import dev.kord.core.event.interaction.ChatInputCommandInteractionCreateEvent
import dev.kord.rest.builder.interaction.GlobalChatInputCreateBuilder
import dev.kord.rest.builder.interaction.user

@Command
class LickCommand(
    private val lickService: LickService,
    private val lickResponse: LickResponse,
) : SlashCommand() {

    override val name = "lick"
    override val description = "Lick a user"

    override fun GlobalChatInputCreateBuilder.buildDefinition() {
        user("lickee", "User to lick") {
            required = true
        }
    }

    override suspend fun handle(
        event: ChatInputCommandInteractionCreateEvent,
        ack: DeferredPublicMessageInteractionResponseBehavior,
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
