package com.iafenvoy.sponsor.mixin;

import com.iafenvoy.sponsor.SponsorManager;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
    @Shadow
    public abstract GameProfile getGameProfile();

    @Inject(method = "getName", at = @At("RETURN"))
    private void modifyDirectName(CallbackInfoReturnable<Text> cir) {
        UUID uuid = this.getGameProfile().getId();
        SponsorManager.PatreonType type = SponsorManager.DATA.get(uuid.toString().replaceAll("-", ""));
        if (type != null && type != SponsorManager.PatreonType.None && cir.getReturnValue() instanceof MutableText mutable)
            mutable.append(Text.literal(type.getIcon()).setStyle(Style.EMPTY.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.literal(type.getName()))).withColor(type.getColor())));
    }
}
