package com.iafenvoy.sponsor;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.iafenvoy.sponsor.util.NetworkUtil;
import net.minecraft.util.Formatting;

import java.util.HashMap;
import java.util.Map;

public class SponsorManager {
    public static final Map<String, PatreonType> DATA = new HashMap<>();

    public static void fetchSponsorList() {
        try {
            SponsorCore.LOGGER.info("Starting to fetch sponsor list.");
            String data = NetworkUtil.getData("https://sponsor.iafenvoy.com/sponsor.json");
            JsonArray array = JsonParser.parseString(data).getAsJsonArray();
            for (JsonElement element : array.asList())
                if (element instanceof JsonObject object && object.has("Patreon") && object.has("McUuid") && PatreonType.values().length > object.get("Patreon").getAsInt())
                    DATA.put(object.get("McUuid").getAsString(), PatreonType.values()[object.get("Patreon").getAsInt()]);
            SponsorCore.LOGGER.info("Successfully fetch sponsor list.");
        } catch (Exception e) {
            SponsorCore.LOGGER.error("Failed to fetch sponsor list.", e);
        }
    }

    @SuppressWarnings("unused")
    public enum PatreonType {
        None("", Formatting.BLACK, ""),
        Coal("Coal Sponsor", Formatting.DARK_GRAY, "⚡"),
        Iron("Iron Sponsor", Formatting.WHITE, "⚡"),
        Gold("Gold Sponsor", Formatting.YELLOW, "⚡"),
        Diamond("Diamond Sponsor", Formatting.AQUA, "⚡"),
        Obsidian("Obsidian Sponsor", Formatting.LIGHT_PURPLE, "⚡"),
        Netherite("Netherite Sponsor", Formatting.DARK_RED, "⚡"),
        Bedrock("Bedrock Sponsor", Formatting.GRAY, "⚡"),
        Void("Void Sponsor", Formatting.BLACK, "⚡"),
        Helper("Mod Helper", Formatting.GREEN, "⚝"),
        Contributor("Mod Contributor", Formatting.BLUE, "⚝"),
        Owner("Mod Author", Formatting.AQUA, "⚝");

        private final String name;
        private final Formatting color;
        private final String icon;

        PatreonType(String name, Formatting color, String icon) {
            this.name = name;
            this.color = color;
            this.icon = icon;
        }

        public Formatting getColor() {
            return color;
        }

        public String getIcon() {
            return icon;
        }

        public String getName() {
            return name;
        }
    }
}
