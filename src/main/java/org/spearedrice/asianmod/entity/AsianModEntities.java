package org.spearedrice.asianmod.entity;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import org.spearedrice.asianmod.AsianMod;

public class AsianModEntities {

    public static final EntityType<SlipperEntity> SLIPPER =
            Registry.register(
                    BuiltInRegistries.ENTITY_TYPE,
                    Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, "slipper"),
                    EntityType.Builder.<SlipperEntity>of(SlipperEntity::new, MobCategory.MISC)
                            .sized(0.25F, 0.25F)
                            .build("slipper")
            );

    public static void init() {}
}