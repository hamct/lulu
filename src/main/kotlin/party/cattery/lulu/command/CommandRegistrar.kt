package party.cattery.lulu.command

import dev.kord.core.Kord
import dev.kord.core.event.interaction.ChatInputCommandInteractionCreateEvent
import dev.kord.core.on

import org.springframework.stereotype.Component

@Component
class CommandRegistrar(
    private val commands: List<SlashCommand>,
) {

    suspend fun registerAll(kord: Kord) {
        commands.forEach { command ->
            kord.createGlobalChatInputCommand(command.name, command.description) {
                with(command) {
                    buildDefinition()
                }
            }
        }

        kord.on<ChatInputCommandInteractionCreateEvent> {
            val name = interaction.command.rootName
            commands.find { it.name == name }?.execute(this)
        }
    }
}
