package org.spearedrice.asianmod.datagen;

import java.util.concurrent.CompletableFuture;

import com.google.gson.JsonObject;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

import org.spearedrice.asianmod.damage.AsianModDamageTypes;


public class AsianModDamageTypesProvider {


    public static final DamageType RICE_WINE_DAMAGE_TYPE =
            new DamageType(
                    "rice_wine",
                    DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER,
                    0.1f
            );


    public static class RiceWineDamageTypeTagGenerator extends FabricTagProvider<DamageType> {

        public RiceWineDamageTypeTagGenerator(FabricDataOutput output,
                                              CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, Registries.DAMAGE_TYPE, registriesFuture);
        }

        @Override
        protected void addTags(HolderLookup.Provider lookup) {
            builder(TagKey.create(
                    Registries.DAMAGE_TYPE,
                    Identifier.fromNamespaceAndPath("minecraft", "bypasses_armor")
            )).add(AsianModDamageTypes.RICE_WINE_DAMAGE);
        }
    }

    public static class RiceWineDamageTypesGenerator implements DataProvider {
        private final PackOutput.PathProvider path;

        RiceWineDamageTypesGenerator(FabricDataOutput fabricDataOutput) {
            path = fabricDataOutput.createPathProvider(PackOutput.Target.DATA_PACK, "damage_type/");
        }

        @Override
        public CompletableFuture<?> run(CachedOutput writer) {
            JsonObject damageTypeObject = new JsonObject();

            damageTypeObject.addProperty("exhaustion", RICE_WINE_DAMAGE_TYPE.exhaustion());
            damageTypeObject.addProperty("message_id", RICE_WINE_DAMAGE_TYPE.msgId());
            damageTypeObject.addProperty("scaling", RICE_WINE_DAMAGE_TYPE.scaling().getSerializedName());

            return DataProvider.saveStable(writer, damageTypeObject, path.json(Identifier.fromNamespaceAndPath("asianmod", "rice_wine")));
        }

        @Override
        public String getName() {
            return "AsianModDamageTypesGenerator";
        }
    }
}