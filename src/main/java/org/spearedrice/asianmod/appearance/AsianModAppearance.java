package org.spearedrice.asianmod.appearance;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import net.fabricmc.api.ModInitializer;

import org.spearedrice.asianmod.AsianMod;


public class AsianModAppearance implements ModInitializer {

    private static final ResourceKey<Item> LINGZHI_BLOCK_ITEM_KEY = ResourceKey.create(
            Registries.ITEM,
            Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, "lingzhi")
    );

    private static final ResourceKey<Block> LINGZHI_BLOCK_KEY = ResourceKey.create(
            Registries.BLOCK,
            Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, "lingzhi")
    );

    public static final Block LINGZHI_BLOCK = Registry.register(
            BuiltInRegistries.BLOCK,
            Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, "lingzhi"),
            new Block(BlockBehaviour.Properties.of()
                    .noCollision()
                    .instabreak()
                    .offsetType(BlockBehaviour.OffsetType.XYZ)
                    .setId(LINGZHI_BLOCK_KEY)
            )
    );

    public static final Item LINGZHI_BLOCK_ITEM = Registry.register(
            BuiltInRegistries.ITEM,
            Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, "lingzhi"),
            new BlockItem(LINGZHI_BLOCK, new Item.Properties()
                    .setId(LINGZHI_BLOCK_ITEM_KEY)
            )
    );

    @Override
    public void onInitialize() {
    }
}