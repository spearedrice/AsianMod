package org.spearedrice.asianmod.entity.model;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.Identifier;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;

import org.spearedrice.asianmod.AsianMod;

public class ModEntityModelLayers {

    public static final ModelLayerLocation ASIAN_DAD =
            createMain("asian_dad");

    private static ModelLayerLocation createMain(String name) {
        return new ModelLayerLocation(
                Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, name),
                "main"
        );
    }

    public static void registerModelLayers() {
        EntityModelLayerRegistry.registerModelLayer(
                ASIAN_DAD,
                AsianDadEntityModel::getTexturedModelData
        );
    }
}