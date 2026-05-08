package org.spearedrice.asianmod.block;

import java.util.function.Function;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;

import net.minecraft.world.level.block.state.properties.BlockSetType;
import org.spearedrice.asianmod.AsianMod;
import org.spearedrice.asianmod.block.custom.*;
import org.spearedrice.asianmod.block.custom.CounterBlock;
import org.spearedrice.asianmod.block.custom.DirtChestBlock;
import org.spearedrice.asianmod.block.custom.EngineBlock;
import org.spearedrice.asianmod.block.custom.PrismarineLampBlock;
import org.spearedrice.asianmod.fluid.ModFluids;

public class ModBlocks {

    // ACID → MERCURY (same logic)
    public static final Block MERCURY = register(
            "mercury",
            (props) -> new LiquidBlock(ModFluids.MERCURY_STILL, props),
            BlockBehaviour.Properties.ofFullCopy(Blocks.WATER),
            false
    );

    public static final Block RAMMED_EARTH = register(
            "rammed_earth",
            Block::new,
            BlockBehaviour.Properties.of(),
            true
    );

    public static final Block COMPACTED_TIMBER = register(
            "compacted_timber",
            RotatedPillarBlock::new,
            BlockBehaviour.Properties.of(),
            true
    );

    public static final Block ABACUS_BLOCK = register(
            "abacus_block",
            Block::new,
            BlockBehaviour.Properties.of(),
            true
    );

    public static final Block BRASS_CHEST = register(
            "brass_chest",
            BrassChestBlock::new,
            BlockBehaviour.Properties.of(),
            true
    );

    public static final Block BELLOWS_BLOCK = register(
            "bellows_block",
            BellowsBlock::new,
            BlockBehaviour.Properties.of().setId(BELLOWS_BLOCK_KEY),
            true
    );

    public static final Block CLAY_PIPE_BLOCK = register(
            "clay_pipe_block",
            Block::new,
            BlockBehaviour.Properties.of(),
            true
    );

    public static final Block OIL_LAMP = register(
            "oil_lamp",
            OilLampBlock::new,
            BlockBehaviour.Properties.of(),
            true
    );


    public static final Block PORCELAIN_ORE =
            Registry.register(BuiltInRegistries.BLOCK,
                    Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, "porcelain_ore"),
                    new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE)));

    public static final Block PORCELAIN_LOG =
            Registry.register(BuiltInRegistries.BLOCK,
                    Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, "porcelain_log"),
                    new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));

    public static final Block PORCELAIN_LEAVES =
            Registry.register(BuiltInRegistries.BLOCK,
                    Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, "porcelain_leaves"),
                    new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));


    public static final Block PORCELAIN_BLOCK = register(
            "porcelain_block",
            Block::new,
            BlockBehaviour.Properties.of(),
            true
    );

    public static final Block PORCELAIN_DOOR = register(
            "porcelain_door",
            settings -> new DoorBlock(BlockSetType.STONE, settings),
            BlockBehaviour.Properties.of(),
            true
    );

    public static final Block PORCELAIN_FENCE = register(
            "porcelain_fence",
            FenceBlock::new,
            BlockBehaviour.Properties.of(),
            true
    );

    public static final Block PORCELAIN_SLAB = register(
            "porcelain_slab",
            SlabBlock::new,
            BlockBehaviour.Properties.of(),
            true
    );

    public static final Block PORCELAIN_STAIRS = register(
            "porcelain_stairs",
            settings -> new StairBlock(PORCELAIN_BLOCK.defaultBlockState(), settings),
            BlockBehaviour.Properties.of(),
            true
    );

    public static final Block PORCELAIN_TRAPDOOR = register(
            "porcelain_trapdoor",
            settings -> new TrapDoorBlock(BlockSetType.STONE, settings),
            BlockBehaviour.Properties.of(),
            true
    );

    public static final Block VERTICAL_OAK_LOG_SLAB = register(
            "vertical_oak_log_slab",
            VerticalSlabBlock::new,
            BlockBehaviour.Properties.of(),
            true
    );

    // RUBY → NEPHRITE (same structure, same family style)
    public static final Block NEPHRITE_BLOCK = register(
            "nephrite_block",
            Block::new,
            BlockBehaviour.Properties.of(),
            true
    );

    public static final Block NEPHRITE_STAIRS = register(
            "nephrite_stairs",
            settings -> new StairBlock(NEPHRITE_BLOCK.defaultBlockState(), settings),
            BlockBehaviour.Properties.of(),
            true
    );

    public static final Block NEPHRITE_SLAB = register(
            "nephrite_slab",
            SlabBlock::new,
            BlockBehaviour.Properties.of(),
            true
    );

    public static final Block NEPHRITE_FENCE = register(
            "nephrite_fence",
            FenceBlock::new,
            BlockBehaviour.Properties.of(),
            true
    );

    public static final Block NEPHRITE_DOOR = register(
            "nephrite_door",
            settings -> new DoorBlock(BlockSetType.STONE, settings),
            BlockBehaviour.Properties.of(),
            true
    );

    public static final Block NEPHRITE_TRAPDOOR = register(
            "nephrite_trapdoor",
            settings -> new TrapDoorBlock(BlockSetType.STONE, settings),
            BlockBehaviour.Properties.of(),
            true
    );

    public static final Block DUPLICATOR_BLOCK = register(
            "duplicator",
            DuplicatorBlock::new,
            BlockBehaviour.Properties.of(),
            true
    );


    private static Block register(String name, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties settings, boolean item) {

        ResourceKey<Block> blockKey = ResourceKey.create(
                Registries.BLOCK,
                Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, name)
        );

        Block block = factory.apply(settings.setId(blockKey));

        if (item) {
            ResourceKey<Item> itemKey = ResourceKey.create(
                    Registries.ITEM,
                    Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, name)
            );

            BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix());
            Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
        }

        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(net.minecraft.world.item.CreativeModeTabs.BUILDING_BLOCKS)
                .register(entries -> {
                    entries.accept(RAMMED_EARTH);
                    entries.accept(COMPACTED_TIMBER);
                    entries.accept(ABACUS_BLOCK);
                    entries.accept(BRASS_CHEST);
                    entries.accept(BELLOWS_BLOCK);
                    entries.accept(CLAY_PIPE_BLOCK);
                    entries.accept(OIL_LAMP);
                    entries.accept(PORCELAIN_ORE);
                    entries.accept(PORCELAIN_LOG);
                    entries.accept(PORCELAIN_LEAVES);
                    entries.accept(PORCELAIN_BLOCK);
                    entries.accept(PORCELAIN_STAIRS);
                    entries.accept(PORCELAIN_SLAB);
                    entries.accept(PORCELAIN_FENCE);
                    entries.accept(PORCELAIN_DOOR);
                    entries.accept(PORCELAIN_TRAPDOOR);
                    entries.accept(VERTICAL_OAK_LOG_SLAB);

                    entries.accept(NEPHRITE_BLOCK);
                    entries.accept(NEPHRITE_STAIRS);
                    entries.accept(NEPHRITE_SLAB);
                    entries.accept(NEPHRITE_FENCE);
                    entries.accept(NEPHRITE_DOOR);
                    entries.accept(NEPHRITE_TRAPDOOR);

                    entries.accept(MERCURY);
                });
    }
}