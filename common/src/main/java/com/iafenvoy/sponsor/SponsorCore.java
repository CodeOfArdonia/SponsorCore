package com.iafenvoy.sponsor;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

public final class SponsorCore {
    public static final String MOD_ID = "sponsor_core";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static void init() {
        new Thread(SponsorManager::fetchSponsorList).start();
    }
}
