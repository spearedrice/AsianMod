package org.spearedrice.asianmod.datagen;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.damagesource.DamageType;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;

import org.spearedrice.asianmod.damage.AsianModDamageTypes;

public class AsianModDamageTypeGenerator extends FabricDynamicRegistryProvider {

    public AsianModDamageTypeGenerator(
            FabricDataOutput output,
            CompletableFuture<HolderLookup.Provider> registriesFuture
    ) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.addAll(registries.lookupOrThrow(Registries.DAMAGE_TYPE));
    }

    @Override
    public String getName() {
        return "AsianMod Damage Types";
    }

    public static void bootstrap(BootstrapContext<DamageType> context) {
        context.register(
                AsianModDamageTypes.RICE_WINE_DAMAGE,
                new DamageType(
                        "rice_wine",
                        net.minecraft.world.damagesource.DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER,
                        0.1f
                )
        );
    }
}
