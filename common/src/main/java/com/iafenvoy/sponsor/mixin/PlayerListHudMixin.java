package com.iafenvoy.sponsor.mixin;

import com.iafenvoy.sponsor.SponsorManager;
import net.minecraft.client.gui.hud.PlayerListHud;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(PlayerListHud.class)
public class PlayerListHudMixin {
    @Inject(method = "getPlayerName", at = @At("RETURN"))
    private void modifyName(PlayerListEntry entry, CallbackInfoReturnable<Text> cir) {
        UUID uuid = entry.getProfile().getId();
        SponsorManager.PatreonType type = SponsorManager.DATA.get(uuid.toString().replaceAll("-", ""));
        if (type != null && type != SponsorManager.PatreonType.None && cir.getReturnValue() instanceof MutableText mutable)
            mutable.append(Text.literal(type.getIcon()).setStyle(Style.EMPTY.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.literal(type.getName()))).withColor(type.getColor())));
    }
}
