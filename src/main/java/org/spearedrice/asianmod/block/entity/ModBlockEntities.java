package org.spearedrice.asianmod.block.entity;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;

import org.spearedrice.asianmod.AsianMod;
import org.spearedrice.asianmod.block.ModBlocks;
import org.spearedrice.asianmod.block.entity.custom.CounterBlockEntity;
import org.spearedrice.asianmod.block.entity.custom.DirtChestBlockEntity;
import org.spearedrice.asianmod.block.entity.custom.DuplicatorBlockEntity;
import org.spearedrice.asianmod.block.entity.custom.BellowsBlockEntity;

public class ModBlockEntities {

    public static final BlockEntityType<BellowsBlockEntity> BELLOW_BLOCK_ENTITY =
            register("bellows", BellowsBlockEntity::new, ModBlocks.BELLOWS_BLOCK);

    public static final BlockEntityType<DuplicatorBlockEntity> DUPLICATOR_BLOCK_ENTITY =
            register("duplicator", DuplicatorBlockEntity::new, ModBlocks.DUPLICATOR_BLOCK);

    public static final BlockEntityType<DirtChestBlockEntity> BRASS_CHEST_BLOCK_ENTITY =
            register("brass_chest", DirtChestBlockEntity::new, ModBlocks.BRASS_CHEST_BLOCK);

    public static final BlockEntityType<CounterBlockEntity> ABACUS_BLOCK_ENTITY =
            register("abacus_block", CounterBlockEntity::new, ModBlocks.ABACUS_BLOCK);

    private static <T extends BlockEntity> BlockEntityType<T> register(
            String name,
            FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory,
            Block... blocks
    ) {
        Identifier id = Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, name);
        return Registry.register(
                BuiltInRegistries.BLOCK_ENTITY_TYPE,
                id,
                FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build()
        );
    }

    public static void initialize() {}
}