package me.keet.compactgolems;

import me.keet.compactgolems.items.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Compactgolems implements ModInitializer {

    public static final String MOD_ID = "compact-golems";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.initialize();
    }
}
