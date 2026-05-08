package org.spearedrice.asianmod.debug;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import net.fabricmc.api.ModInitializer;

public class AsianModDebug implements ModInitializer {

    public static final String MOD_ID = "asianmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {

        LOGGER.debug("Debug logging is enabled");

        Identifier identifier = Identifier.fromNamespaceAndPath(MOD_ID, "teto_baguette");
        ResourceKey<Item> testItemKey = ResourceKey.create(Registries.ITEM, identifier);

        Registry.register(
                BuiltInRegistries.ITEM,
                identifier,
                new TetoBaguette(
                        new Item.Properties()
                                .setId(testItemKey)
                                .stacksTo(1)
                                .rarity(Rarity.EPIC)
                                .component(
                                        DataComponents.CUSTOM_NAME,
                                        Component.literal("[Use on Stone Block]")
                                )
                )
        );
    }
}