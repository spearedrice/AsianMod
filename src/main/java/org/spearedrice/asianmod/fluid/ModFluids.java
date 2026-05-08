package org.spearedrice.asianmod.fluid;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.material.FlowingFluid;

import org.spearedrice.asianmod.AsianMod;
import org.spearedrice.asianmod.fluid.custom.MercuryFluid;

public class ModFluids {
    public static final FlowingFluid MERCURY_FLOWING = register("flowing_mercury", new MercuryFluid.Flowing());
    public static final FlowingFluid MERCURY_STILL = register("mercury", new MercuryFluid.Source());

    private static FlowingFluid register(String name, FlowingFluid fluid) {
        return Registry.register(BuiltInRegistries.FLUID, Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, name), fluid);
    }

    public static void initialize() {
    }
}