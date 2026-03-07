package org.spearedrice.asianmod.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import org.spearedrice.asianmod.AsianMod;
import org.spearedrice.asianmod.block.custom.AsianBlock;

public class ModBlocks {

    public static final Block NEPHRITE_JADE_BLOCK = register(
            "nephrite_jade_block",
            settings -> new Block(settings),
            AbstractBlock.Settings.create()
                    .strength(4f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
    );

    public static final Block RAW_NEPHRITE_JADE_BLOCK = register(
            "raw_nephrite_jade_block",
            settings -> new Block(settings),
            AbstractBlock.Settings.create()
                    .strength(3f)
                    .requiresTool()
    );

    public static final Block NEPHRITE_JADE_ORE = register(
            "nephrite_jade_ore",
            settings -> new ExperienceDroppingBlock(UniformIntProvider.create(2, 5), settings),
            AbstractBlock.Settings.create()
                    .strength(3f)
                    .requiresTool()
    );

    public static final Block NEPHRITE_JADE_DEEPSLATE_ORE = register(
            "nephrite_jade_deepslate_ore",
            settings -> new ExperienceDroppingBlock(UniformIntProvider.create(3, 6), settings),
            AbstractBlock.Settings.create()
                    .strength(4f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.DEEPSLATE)
    );

    public static final Block ASIAN_BLOCK = register(
            "asian_block",
            AsianBlock::new,
            AbstractBlock.Settings.create()
                    .strength(1f)
                    .requiresTool()
    );

    private static <T extends Block> T register(
            String name,
            java.util.function.Function<AbstractBlock.Settings, T> factory,
            AbstractBlock.Settings settings
    ) {
        Identifier id = Identifier.of(AsianMod.MOD_ID, name);
        RegistryKey<Block> blockKey = RegistryKey.of(RegistryKeys.BLOCK, id);

        settings = settings.registryKey(blockKey);

        T block = factory.apply(settings);
        Registry.register(Registries.BLOCK, blockKey, block);

        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, id);
        Registry.register(Registries.ITEM, itemKey,
                new BlockItem(block, new Item.Settings()
                        .registryKey(itemKey)
                        .useBlockPrefixedTranslationKey()
                )
        );

        return block;
    }

    public static void registerModBlocks() {
        AsianMod.LOGGER.info("Registering blocks for " + AsianMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(NEPHRITE_JADE_BLOCK);
            entries.add(RAW_NEPHRITE_JADE_BLOCK);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.add(RAW_NEPHRITE_JADE_BLOCK);
            entries.add(NEPHRITE_JADE_ORE);
            entries.add(NEPHRITE_JADE_DEEPSLATE_ORE);
        });
    }
}