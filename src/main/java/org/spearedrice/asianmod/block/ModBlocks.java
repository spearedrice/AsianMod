package org.spearedrice.asianmod.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import org.spearedrice.asianmod.AsianMod;

public class ModBlocks {

    public static final Block NEPHRITE_JADE_BLOCK = registerBlock(
            "nephrite_jade_block",
            new Block(AbstractBlock.Settings.create()
                    .registryKey(RegistryKey.of(
                            RegistryKeys.BLOCK,
                            Identifier.of(AsianMod.MOD_ID, "nephrite_jade_block")
                    ))
                    .strength(4f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
            )
    );

    public static final Block RAW_NEPHRITE_JADE_BLOCK = registerBlock(
            "raw_nephrite_jade_block",
            new Block(AbstractBlock.Settings.create()
                    .registryKey(RegistryKey.of(
                            RegistryKeys.BLOCK,
                            Identifier.of(AsianMod.MOD_ID, "raw_nephrite_jade_block")
                    ))
                    .strength(3f)
                    .requiresTool()
            )
    );
    private static Block registerBlock(String name, Block block) {
        Identifier id = Identifier.of(AsianMod.MOD_ID, name);

        Registry.register(Registries.BLOCK, id, block);
        registerBlockItem(name, block);

        return block;
    }

    private static void registerBlockItem(String name, Block block) {
        Identifier id = Identifier.of(AsianMod.MOD_ID, name);

        Registry.register(Registries.ITEM, id,
                new BlockItem(block,
                        new Item.Settings()
                                .registryKey(RegistryKey.of(RegistryKeys.ITEM, id))
                                .useBlockPrefixedTranslationKey()
                )
        );
    }

    public static void registerModBlocks() {
        AsianMod.LOGGER.info("Registering " + AsianMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(NEPHRITE_JADE_BLOCK);
            entries.add(RAW_NEPHRITE_JADE_BLOCK);
        });
    }
}

// swear to god all this needs is RegistryKey