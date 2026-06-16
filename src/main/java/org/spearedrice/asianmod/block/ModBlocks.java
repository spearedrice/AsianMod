package org.spearedrice.asianmod.block;

import java.util.function.Function;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.BlockFamily;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;

import org.spearedrice.asianmod.AsianMod;
import org.spearedrice.asianmod.block.custom.*;
import org.spearedrice.asianmod.fluid.ModFluids;

public class ModBlocks {

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

	public static final ResourceKey<Block> BELLOWS_BLOCK_KEY = ResourceKey.create(
			Registries.BLOCK,
			Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, "bellows_block")
	);
	public static final Block BELLOWS_BLOCK = register(
			"bellows_block",
			BellowsBlock::new,
			BlockBehaviour.Properties.of().setId(BELLOWS_BLOCK_KEY),
			true
	);

	public static final Block CLAY_PIPE = register(
			"clay_pipe",
			RotatedPillarBlock::new,
			BlockBehaviour.Properties.of(),
			true
	);

	public static final Block OIL_LAMP = register(
			"oil_lamp",
			OilLampBlock::new,
			BlockBehaviour.Properties.of(),
			true
	);

	public static final Block PORCELAIN_ORE = register(
			"porcelain_ore",
			Block::new,
			BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE),
			true
	);

	public static final Block PORCELAIN_LOG = register(
			"porcelain_log",
			RotatedPillarBlock::new,
			BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG),
			true
	);

	public static final Block PORCELAIN_LEAVES = register(
			"porcelain_leaves",
			PorcelainLeavesBlock::new,
			BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES),
			true
	);

	public static final Block PORCELAIN_BLOCK = register(
			"porcelain_block", Block::new, BlockBehaviour.Properties.of(), true
	);
	public static final Block PORCELAIN_STAIRS = register(
			"porcelain_stairs", settings -> new StairBlock(PORCELAIN_BLOCK.defaultBlockState(), settings), BlockBehaviour.Properties.of(), true
	);
	public static final Block PORCELAIN_SLAB = register(
			"porcelain_slab", SlabBlock::new, BlockBehaviour.Properties.of(), true
	);
	public static final Block PORCELAIN_FENCE = register(
			"porcelain_fence", FenceBlock::new, BlockBehaviour.Properties.of(), true
	);


	public static final Block NEPHRITE_BLOCK = register(
			"nephrite_block", Block::new, BlockBehaviour.Properties.of(), true
	);


	public static final Block NEPHRITE_JADE_BLOCK = register(
			"nephrite_jade_block", Block::new, BlockBehaviour.Properties.of(), true
	);

	public static final Block RAW_NEPHRITE_JADE_BLOCK = register(
			"raw_nephrite_jade_block", Block::new, BlockBehaviour.Properties.of(), true
	);

	public static final Block NEPHRITE_JADE_ORE = register(
			"nephrite_jade_ore",
			Block::new,
			BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE),
			true
	);

	public static final Block NEPHRITE_JADE_DEEPSLATE_ORE = register(
			"nephrite_jade_deepslate_ore",
			Block::new,
			BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE),
			true
	);

	public static final Block NEPHRITE_DOOR = register(
			"nephrite_door", settings -> new DoorBlock(BlockSetType.STONE, settings), BlockBehaviour.Properties.of(), true
	);

	public static final Block STEEL_BLOCK = register(
			"steel_block",
			Block::new,
			BlockBehaviour.Properties.of(),
			true
	);



	public static final Block VERTICAL_BAMBOO_SLAB = register(
			"vertical_bamboo_slab",
			VerticalSlabBlock::new,
			BlockBehaviour.Properties.of(),
			true
	);

	public static final Block LINGZHI = register(
			"lingzhi",
			Block::new,
			BlockBehaviour.Properties.of(),
			true
	);


	public static final Block SKY_LANTERN_BLOCK = register(
			"sky_lantern",
			Block::new,
			BlockBehaviour.Properties.of(),
			false
	);
	private static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties settings, boolean shouldRegisterItem) {
		ResourceKey<Block> blockKey = keyOfBlock(name);
		Block block = blockFactory.apply(settings.setId(blockKey));

		if (shouldRegisterItem) {
			ResourceKey<Item> itemKey = keyOfItem(name);

			BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix());
			Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
		}

		return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
	}

	private static ResourceKey<Block> keyOfBlock(String name) {
		return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, name));
	}

	private static ResourceKey<Item> keyOfItem(String name) {
		return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, name));
	}

	public static void initialize() {
		setupItemGroups();
	}

	public static void setupItemGroups() {
		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register((itemGroup) -> {
			itemGroup.accept(ModBlocks.RAMMED_EARTH.asItem());
			itemGroup.accept(ModBlocks.COMPACTED_TIMBER.asItem());
			itemGroup.accept(ModBlocks.ABACUS_BLOCK.asItem());
			itemGroup.accept(ModBlocks.BRASS_CHEST.asItem());
			itemGroup.accept(ModBlocks.BELLOWS_BLOCK.asItem());
			itemGroup.accept(ModBlocks.CLAY_PIPE.asItem());
			itemGroup.accept(ModBlocks.OIL_LAMP.asItem());
			itemGroup.accept(ModBlocks.PORCELAIN_ORE.asItem());
			itemGroup.accept(ModBlocks.PORCELAIN_LOG.asItem());
			itemGroup.accept(ModBlocks.PORCELAIN_LEAVES.asItem());
			itemGroup.accept(ModBlocks.PORCELAIN_BLOCK.asItem());
			itemGroup.accept(ModBlocks.PORCELAIN_STAIRS.asItem());
			itemGroup.accept(ModBlocks.PORCELAIN_SLAB.asItem());
			itemGroup.accept(ModBlocks.PORCELAIN_FENCE.asItem());

			itemGroup.accept(ModBlocks.NEPHRITE_BLOCK.asItem());
			itemGroup.accept(ModBlocks.NEPHRITE_DOOR.asItem());

			itemGroup.accept(ModBlocks.NEPHRITE_JADE_BLOCK.asItem());
			itemGroup.accept(ModBlocks.RAW_NEPHRITE_JADE_BLOCK.asItem());
			itemGroup.accept(ModBlocks.NEPHRITE_JADE_ORE.asItem());
			itemGroup.accept(ModBlocks.NEPHRITE_JADE_DEEPSLATE_ORE.asItem());
		});
	}

}