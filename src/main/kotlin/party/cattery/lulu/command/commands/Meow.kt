package party.cattery.lulu.command.commands

import party.cattery.lulu.annotation.Command
import party.cattery.lulu.command.SlashCommand

import dev.kord.core.behavior.interaction.response.respond
import dev.kord.core.event.interaction.ChatInputCommandInteractionCreateEvent

@Command
class Meow : SlashCommand {

    override val name = "meow"
    override val description = "meow meow"

    override suspend fun execute(event: ChatInputCommandInteractionCreateEvent) {
        val interaction = event.interaction
        val response = interaction.deferPublicResponse()
        response.respond {
            content = "meooow"
        }
    }
}
