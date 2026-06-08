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
    public static final FlowingFluid RICE_WINE_FLOWING = register("flowing_rice_wine", new org.spearedrice.asianmod.fluid.custom.RiceWineFluid.Flowing());
    public static final FlowingFluid RICE_WINE_STILL = register("rice_wine", new org.spearedrice.asianmod.fluid.custom.RiceWineFluid.Source());

    private static FlowingFluid register(String name, FlowingFluid fluid) {
        return Registry.register(BuiltInRegistries.FLUID, Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, name), fluid);
    }

    public static void initialize() {
    }
}