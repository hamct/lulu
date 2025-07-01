package party.cattery.lulu.command

import party.cattery.lulu.command.commands.message.MessageCommand
import party.cattery.lulu.command.commands.slash.SlashCommand
import party.cattery.lulu.command.commands.user.UserCommand

import dev.kord.core.Kord
import dev.kord.core.event.interaction.ChatInputCommandInteractionCreateEvent
import dev.kord.core.event.interaction.MessageCommandInteractionCreateEvent
import dev.kord.core.event.interaction.UserCommandInteractionCreateEvent
import dev.kord.core.on

import org.springframework.stereotype.Component

@Component
class CommandRegistrar(
    private val commands: List<BaseCommand>,
) {
    final inline fun <reified T : BaseCommand> List<BaseCommand>.toCommandMap(): Map<String, T> =
        this.filterIsInstance<T>().associateBy { it.name }

    private val slashCommands: Map<String, SlashCommand> = commands.toCommandMap<SlashCommand>()
    private val userCommands: Map<String, UserCommand> = commands.toCommandMap<UserCommand>()
    private val messageCommands: Map<String, MessageCommand> = commands.toCommandMap<MessageCommand>()

    suspend fun registerAll(kord: Kord) {
        commands.forEach { command -> command.register(kord) }
        registerDispatchHandler(kord)
    }

    private fun registerDispatchHandler(kord: Kord) {
        kord.on<ChatInputCommandInteractionCreateEvent> {
            slashCommands[interaction.command.rootName]?.execute(this)
        }

        kord.on<UserCommandInteractionCreateEvent> {
            userCommands[interaction.invokedCommandName]?.execute(this)
        }

        kord.on<MessageCommandInteractionCreateEvent> {
            messageCommands[interaction.invokedCommandName]?.execute(this)
        }
    }
}
