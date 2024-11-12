package com.iafenvoy.sponsor.forge;

import com.iafenvoy.sponsor.SponsorCore;
import net.neoforged.fml.common.Mod;

@Mod(SponsorCore.MOD_ID)
public final class SponsorCoreForge {
    public SponsorCoreForge() {
        SponsorCore.init();
    }
}
