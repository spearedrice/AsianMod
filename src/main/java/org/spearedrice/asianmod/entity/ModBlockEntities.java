package org.spearedrice.asianmod.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntityType;

import org.spearedrice.asianmod.AsianMod;
import org.spearedrice.asianmod.block.ModBlocks;
import org.spearedrice.asianmod.block.entity.custom.BellowsBlockEntity;

public class ModBlockEntities {

    public static final BlockEntityType<BellowsBlockEntity> BELLOWS_BLOCK_ENTITY =
            Registry.register(
                    BuiltInRegistries.BLOCK_ENTITY_TYPE,
                    Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, "bellows_block_entity"),
                    FabricBlockEntityTypeBuilder.create(
                            BellowsBlockEntity::new,
                            ModBlocks.BELLOWS_BLOCK
                    ).build()
            );

    public static void initialize() {}
}