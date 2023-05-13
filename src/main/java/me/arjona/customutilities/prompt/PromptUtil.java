package me.arjona.customutilities.prompt;

import lombok.experimental.UtilityClass;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.conversations.Prompt;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

@UtilityClass
public class PromptUtil {

    public void beginPrompt(JavaPlugin plugin, Player player, Prompt prompt, Map<Object, Object> data) {
        getFactory(plugin)
                .withFirstPrompt(prompt)
                .withInitialSessionData(data)
                .buildConversation(player)
                .begin();
    }

    private ConversationFactory getFactory(JavaPlugin plugin) {
        return new ConversationFactory(plugin);
    }
}
