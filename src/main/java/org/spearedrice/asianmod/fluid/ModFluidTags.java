package org.spearedrice.asianmod.fluid;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;

import org.spearedrice.asianmod.AsianMod;

public class ModFluidTags {
    public static final TagKey<Fluid> MERCURY =
            TagKey.create(Registries.FLUID, Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, "mercury"));
}