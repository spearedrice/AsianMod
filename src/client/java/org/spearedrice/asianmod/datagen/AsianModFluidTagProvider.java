package org.spearedrice.asianmod.datagen;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.tags.FluidTags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

import org.spearedrice.asianmod.fluid.ModFluidTags;
import org.spearedrice.asianmod.fluid.ModFluids;

public class AsianModFluidTagProvider extends FabricTagProvider.FluidTagProvider {

    public AsianModFluidTagProvider(
            FabricDataOutput output,
            CompletableFuture<HolderLookup.Provider> registriesFuture
    ) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {

        valueLookupBuilder(ModFluidTags.MERCURY)
                .add(ModFluids.MERCURY_STILL, ModFluids.MERCURY_FLOWING);


        valueLookupBuilder(FluidTags.LAVA)
                .addTag(ModFluidTags.MERCURY);
    }
}