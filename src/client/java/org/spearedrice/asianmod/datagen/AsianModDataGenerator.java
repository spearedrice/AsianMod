package org.spearedrice.asianmod.datagen;

import static org.spearedrice.asianmod.datagen.AsianModDamageTypesProvider.RICE_WINE_DAMAGE_TYPE;

import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

import org.spearedrice.asianmod.appearance.AsianModAppearanceModelProvider;
import org.spearedrice.asianmod.damage.AsianModDamageTypes;
import org.spearedrice.asianmod.datagen.internal.AsianModInternalModelProvider;
import org.spearedrice.asianmod.networking.basic.AsianModNetworkingBasicModelProvider;
import org.spearedrice.asianmod.worldgen.AsianModWorldConfiguredFeatures;
import org.spearedrice.asianmod.worldgen.AsianModWorldPlacedFeatures;
import org.spearedrice.asianmod.worldgen.AsianModWorldgenProvider;

// :::datagen-setup:generator
public class AsianModDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {

        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        // enchantments
        pack.addProvider(AsianModEnchantmentGenerator::new);

        // advancements
        pack.addProvider(AsianModAdvancementProvider::new);

        // language
        pack.addProvider(AsianModEnglishLangProvider::new);

        // tags
        pack.addProvider(AsianModItemTagProvider::new);
        pack.addProvider(AsianModEnchantmentTagProvider::new);
        pack.addProvider(AsianModFluidTagProvider::new);

        // recipes
        pack.addProvider(AsianModRecipeProvider::new);

        // loot tables
        pack.addProvider(AsianModBlockLootTableProvider::new);
        pack.addProvider(AsianModChestLootTableProvider::new);

        // damage types
        pack.addProvider(AsianModDamageTypesProvider.RiceWineDamageTypesGenerator::new);
        pack.addProvider(AsianModDamageTypesProvider.RiceWineDamageTypeTagGenerator::new);

        // models
        pack.addProvider(AsianModInternalModelProvider::new);
        pack.addProvider(AsianModModelProvider::new);
        pack.addProvider(AsianModAppearanceModelProvider::new);

        // networking
        pack.addProvider(AsianModNetworkingBasicModelProvider::new);

        // worldgen
        pack.addProvider(AsianModWorldgenProvider::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {

        // custom damage type
        registryBuilder.add(Registries.DAMAGE_TYPE, registerable -> {
            registerable.register(
                    AsianModDamageTypes.RICE_WINE_DAMAGE,
                    RICE_WINE_DAMAGE_TYPE
            );
        });

        // worldgen
        registryBuilder.add(
                Registries.CONFIGURED_FEATURE,
                AsianModWorldConfiguredFeatures::configure
        );

        registryBuilder.add(
                Registries.PLACED_FEATURE,
                AsianModWorldPlacedFeatures::configure
        );

        // enchantments
        registryBuilder.add(
                Registries.ENCHANTMENT,
                AsianModEnchantmentGenerator::bootstrap
        );
    }
}
// :::datagen-setup:generator