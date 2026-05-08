package org.spearedrice.asianmod.damage;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import net.fabricmc.api.ModInitializer;

public class AsianModDamageTypes implements ModInitializer {

    public static final ResourceKey<Block> RICE_WINE_BLOCK_KEY =
            ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("asianmod", "rice_wine"));

    public static final Block RICE_WINE_BLOCK =
            new RiceWineBlock(BlockBehaviour.Properties.of().setId(RICE_WINE_BLOCK_KEY));

    public static final ResourceKey<DamageType> RICE_WINE_DAMAGE =
            ResourceKey.create(Registries.DAMAGE_TYPE, Identifier.fromNamespaceAndPath("asianmod", "rice_wine"));

    @Override
    public void onInitialize() {
        Registry.register(
                BuiltInRegistries.BLOCK,
                Identifier.fromNamespaceAndPath("asianmod", "rice_wine"),
                RICE_WINE_BLOCK
        );

        Registry.register(
                BuiltInRegistries.ITEM,
                Identifier.fromNamespaceAndPath("asianmod", "rice_wine"),
                new BlockItem(
                        RICE_WINE_BLOCK,
                        new Item.Properties().setId(
                                ResourceKey.create(Registries.ITEM, RICE_WINE_BLOCK_KEY.identifier())
                        )
                )
        );
    }
}