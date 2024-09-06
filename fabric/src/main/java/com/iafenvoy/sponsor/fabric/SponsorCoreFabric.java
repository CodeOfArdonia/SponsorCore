package com.iafenvoy.sponsor.fabric;

import net.fabricmc.api.ModInitializer;

import com.iafenvoy.sponsor.SponsorCore;

public final class SponsorCoreFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        SponsorCore.init();
    }
}
