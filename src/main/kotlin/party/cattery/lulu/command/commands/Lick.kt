package party.cattery.lulu.command.commands

import party.cattery.lulu.annotation.Command
import party.cattery.lulu.command.SlashCommand

import dev.kord.core.behavior.interaction.response.respond
import dev.kord.core.event.interaction.ChatInputCommandInteractionCreateEvent
import dev.kord.rest.builder.interaction.GlobalChatInputCreateBuilder
import dev.kord.rest.builder.interaction.user
import dev.kord.rest.builder.message.allowedMentions

@Command
class Lick : SlashCommand {

    override val name = "lick"
    override val description = "licky licky"

    override fun GlobalChatInputCreateBuilder.buildDefinition() {
        user("lickee", "User to lick") {
            required = true
        }
    }

    override suspend fun execute(event: ChatInputCommandInteractionCreateEvent) {
        val interaction = event.interaction

        val response = interaction.deferPublicResponse()
        val command = interaction.command
        val user = command.users["lickee"]
        response.respond {
            content = "${interaction.user.mention} licks ${user?.mention}"
            allowedMentions {
            }
        }
    }
}
