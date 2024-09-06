package com.iafenvoy.sponsor.forge;

import net.minecraftforge.fml.common.Mod;

import com.iafenvoy.sponsor.SponsorCore;

@Mod(SponsorCore.MOD_ID)
public final class SponsorCoreForge {
    public SponsorCoreForge() {
        // Run our common setup.
        SponsorCore.init();
    }
}
