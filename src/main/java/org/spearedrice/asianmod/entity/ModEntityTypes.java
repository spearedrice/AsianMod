package org.spearedrice.asianmod.entity;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;

import org.spearedrice.asianmod.AsianMod;


public class ModEntityTypes {
	public static final EntityType<AsianDadEntity> ASIAN_DAD_ENTITY = register(
			"asian_dad",
			EntityType.Builder.<AsianDadEntity>of(AsianDadEntity::new, MobCategory.MISC)
					.sized(0.75f, 1.75f)
	);

	public static final EntityType<SlipperEntity> SLIPPER_ENTITY = register(
			"slipper",
			EntityType.Builder.<SlipperEntity>of(SlipperEntity::new, MobCategory.MISC)
					.sized(0.25f, 0.25f)
	);

	private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder) {
		ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, name));
		return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(key));
	}

	public static void registerModEntityTypes() {
		AsianMod.LOGGER.info("Registering EntityTypes for " + AsianMod.MOD_ID);
	}

	public static void registerAttributes() {
		FabricDefaultAttributeRegistry.register(ASIAN_DAD_ENTITY, AsianDadEntity.createAttributes());
	}
}