package org.spearedrice.asianmod.networking.basic;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;

public class AsianModNetworkingBasicModelProvider extends FabricModelProvider {

    public AsianModNetworkingBasicModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        // Add block models here if needed
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        // Add item models here if needed
    }

    @Override
    public String getName() {
        return "AsianMod Networking Basic Model Provider";
    }
}
