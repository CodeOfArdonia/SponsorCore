package com.iafenvoy.sponsor.neoforge;

import com.iafenvoy.sponsor.SponsorCore;
import net.neoforged.fml.common.Mod;

@Mod(SponsorCore.MOD_ID)
public final class SponsorCoreNeoForge {
    public SponsorCoreNeoForge() {
        SponsorCore.init();
    }
}
