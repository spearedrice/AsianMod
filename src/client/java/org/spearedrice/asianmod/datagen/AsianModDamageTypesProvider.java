package org.spearedrice.asianmod.datagen;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

import org.spearedrice.asianmod.damage.AsianModDamageTypes;

// :::datagen-damage-types
public class AsianModDamageTypesProvider {

    // damage (not json)
    public static final DamageType RICE_WINE_DAMAGE =
            new DamageType(
                    "rice_wine",
                    DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER,
                    0.1f
            );

    // :::tags
    public static class RiceWineDamageTypeTagGenerator extends FabricTagProvider<DamageType> {

        public RiceWineDamageTypeTagGenerator(FabricDataOutput output,
                                              CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, Registries.DAMAGE_TYPE, registriesFuture);
        }

        @Override
        protected void addTags(HolderLookup.Provider lookup) {
            builder(net.minecraft.tags.TagKey.create(
                    Registries.DAMAGE_TYPE,
                    new net.minecraft.resources.ResourceLocation("minecraft", "bypasses_armor")
            )).add(AsianModDamageTypes.RICE_WINE);
        }
    }
}
// :::datagen-damage-types