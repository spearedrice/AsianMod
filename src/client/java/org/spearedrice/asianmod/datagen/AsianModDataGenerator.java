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


public class AsianModDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {

        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();


        pack.addProvider(AsianModEnchantmentGenerator::new);


        pack.addProvider(AsianModAdvancementProvider::new);


        pack.addProvider(AsianModEnglishLangProvider::new);


        pack.addProvider(AsianModItemTagProvider::new);
        pack.addProvider(AsianModEnchantmentTagProvider::new);
        pack.addProvider(AsianModFluidTagProvider::new);


        pack.addProvider(AsianModRecipeProvider::new);


        pack.addProvider(AsianModBlockLootTableProvider::new);
        pack.addProvider(AsianModChestLootTableProvider::new);


        pack.addProvider(AsianModDamageTypeGenerator::new);
        pack.addProvider(AsianModDamageTypesProvider.RiceWineDamageTypeTagGenerator::new);


        pack.addProvider(AsianModInternalModelProvider::new);
        pack.addProvider(AsianModModelProvider::new);
        pack.addProvider(AsianModAppearanceModelProvider::new);


        pack.addProvider(AsianModNetworkingBasicModelProvider::new);


        pack.addProvider(AsianModWorldgenProvider::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {


        registryBuilder.add(Registries.DAMAGE_TYPE, AsianModDamageTypeGenerator::bootstrap);


        registryBuilder.add(
                Registries.CONFIGURED_FEATURE,
                AsianModWorldConfiguredFeatures::configure
        );

        registryBuilder.add(
                Registries.PLACED_FEATURE,
                AsianModWorldPlacedFeatures::configure
        );


        registryBuilder.add(
                Registries.ENCHANTMENT,
                AsianModEnchantmentGenerator::bootstrap
        );
    }
}