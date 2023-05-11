package me.arjona.customutilities;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;

import java.util.*;

@UtilityClass
public class CC {

    private final Map<String, ChatColor> MAP;

    public final String BLUE;
    public final String AQUA;
    public final String YELLOW;
    public final String RED;
    public final String GRAY;
    public final String GOLD;
    public final String GREEN;
    public final String WHITE;
    public final String BLACK;
    public final String BOLD;
    public final String ITALIC;
    public final String UNDER_LINE;
    public final String STRIKE_THROUGH;
    public final String RESET;
    public final String MAGIC;
    public final String DARK_BLUE;
    public final String DARK_AQUA;
    public final String DARK_GRAY;
    public final String DARK_GREEN;
    public final String DARK_PURPLE;
    public final String DARK_RED;
    public final String PINK;
    public final String MENU_BAR;
    public final String CHAT_BAR;
    public final String SB_BAR;
    public final String TAB_BAR;

    static {
        MAP = new HashMap<>();
        MAP.put("pink", ChatColor.LIGHT_PURPLE);
        MAP.put("orange", ChatColor.GOLD);
        MAP.put("purple", ChatColor.DARK_PURPLE);

        for (ChatColor chatColor : ChatColor.values()) {
            MAP.put(chatColor.name().toLowerCase().replace("_", ""), chatColor);
        }

        BLUE = ChatColor.BLUE.toString();
        AQUA = ChatColor.AQUA.toString();
        YELLOW = ChatColor.YELLOW.toString();
        RED = ChatColor.RED.toString();
        GRAY = ChatColor.GRAY.toString();
        GOLD = ChatColor.GOLD.toString();
        GREEN = ChatColor.GREEN.toString();
        WHITE = ChatColor.WHITE.toString();
        BLACK = ChatColor.BLACK.toString();
        BOLD = ChatColor.BOLD.toString();
        ITALIC = ChatColor.ITALIC.toString();
        UNDER_LINE = ChatColor.UNDERLINE.toString();
        STRIKE_THROUGH = ChatColor.STRIKETHROUGH.toString();
        RESET = ChatColor.RESET.toString();
        MAGIC = ChatColor.MAGIC.toString();
        DARK_BLUE = ChatColor.DARK_BLUE.toString();
        DARK_AQUA = ChatColor.DARK_AQUA.toString();
        DARK_GRAY = ChatColor.DARK_GRAY.toString();
        DARK_GREEN = ChatColor.DARK_GREEN.toString();
        DARK_PURPLE = ChatColor.DARK_PURPLE.toString();
        DARK_RED = ChatColor.DARK_RED.toString();
        PINK = ChatColor.LIGHT_PURPLE.toString();
        MENU_BAR = ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "------------------------";
        CHAT_BAR = ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "------------------------------------------------";
        SB_BAR = ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "----------------------";
        TAB_BAR = ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "-----------------";
    }

    public Set<String> getColorNames() {
        return MAP.keySet();
    }

    public ChatColor getColorFromName(String name) {
        if (MAP.containsKey(name.trim().toLowerCase())) {
            return MAP.get(name.trim().toLowerCase());
        }

        ChatColor color;

        try {
            color = ChatColor.valueOf(name.toUpperCase().replace(" ", "_"));
        } catch (Exception e) {
            return null;
        }

        return color;
    }

    public String translate(String in) {
        return ChatColor.translateAlternateColorCodes('&', in);
    }

    public List<String> translate(List<String> lines) {
        List<String> toReturn = new ArrayList<>();

        for (String line : lines) {
            toReturn.add(ChatColor.translateAlternateColorCodes('&', line));
        }

        return toReturn;
    }

    public String[] translate(String[] lines) {
        List<String> toReturn = new ArrayList<>();

        for (String line : lines) {
            if (line != null) {
                toReturn.add(ChatColor.translateAlternateColorCodes('&', line));
            }
        }

        return toReturn.toArray(new String[toReturn.size()]);
    }

    public String strip(String message) {
        return ChatColor.stripColor(message);
    }

    public ChatColor getByName(String name) {
        return Arrays.stream(ChatColor.values()).filter(chatColor -> chatColor.name().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public boolean isColor(net.md_5.bungee.api.ChatColor color) {
        return color != null &&
                (color != net.md_5.bungee.api.ChatColor.STRIKETHROUGH && color != net.md_5.bungee.api.ChatColor.MAGIC
                        && color != net.md_5.bungee.api.ChatColor.BOLD && color != net.md_5.bungee.api.ChatColor.ITALIC
                        && color != net.md_5.bungee.api.ChatColor.UNDERLINE && color != net.md_5.bungee.api.ChatColor.RESET);
    }

    public String capitalize(String str) {
        return WordUtils.capitalize(str);
    }

    public String toReadable(Enum<?> enu) {
        String[] args = enu.name().replace("_", " ").split(" ");

        for (int i=0;i<args.length;i++) {
            args[i] = capitalize(args[i].toLowerCase());
        }

        return String.join(" ", args);
    }

    public String toReadable(String str) {
        String[] args = str.replace("_", " ").replace("-", " ").split(" ");

        for (int i=0;i<args.length;i++) {
            args[i] = capitalize(args[i].toLowerCase());
        }

        return String.join(" ", args);
    }

    public String getFancyList(List<String> list) {
        StringBuilder builder = new StringBuilder();

        for(int i=0;i<list.size();i++) {
            builder.append(list.get(i)).append(i == (list.size() - 2) ? " & " : ", ");
        }

        return builder.substring(0, builder.length() - 2);
    }
}
