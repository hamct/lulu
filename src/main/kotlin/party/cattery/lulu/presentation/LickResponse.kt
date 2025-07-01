package party.cattery.lulu.presentation

import dev.kord.core.entity.User
import dev.kord.core.entity.interaction.ChatInputCommandInteraction
import dev.kord.rest.builder.message.allowedMentions
import dev.kord.rest.builder.message.embed
import dev.kord.rest.builder.message.modify.InteractionResponseModifyBuilder

import org.springframework.stereotype.Component

@Component
class LickResponse {

    fun buildResponse(
        interaction: ChatInputCommandInteraction,
        lickedUser: User,
        timesLicked: Int,
    ): InteractionResponseModifyBuilder.() -> Unit =
        {
            content = "${interaction.user.mention} licked :tongue: ${lickedUser.mention}!"
            embed {
                description = "Total times licked: $timesLicked"
            }
            allowedMentions {
                users.add(lickedUser.id)
            }
        }
}
