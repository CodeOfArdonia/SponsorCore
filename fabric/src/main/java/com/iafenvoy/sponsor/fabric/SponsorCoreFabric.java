package com.iafenvoy.sponsor.fabric;

import net.fabricmc.api.ModInitializer;

import com.iafenvoy.sponsor.SponsorCore;

public final class SponsorCoreFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        SponsorCore.init();
    }
}
