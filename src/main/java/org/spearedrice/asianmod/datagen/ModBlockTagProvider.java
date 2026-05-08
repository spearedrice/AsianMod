package org.spearedrice.asianmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import org.spearedrice.asianmod.block.ModBlocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        // uhh should work ig
        valueLookupBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.NEPHRITE_JADE_BLOCK)
                .add(ModBlocks.RAW_NEPHRITE_JADE_BLOCK)
                .add(ModBlocks.NEPHRITE_JADE_ORE)
                .add(ModBlocks.NEPHRITE_JADE_DEEPSLATE_ORE)
                .add(ModBlocks.ASIAN_BLOCK);
    }
}