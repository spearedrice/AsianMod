package org.spearedrice.asianmod.entity.attribute;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;

import org.spearedrice.asianmod.AsianMod;

public class ModAttributes {

    // attributes
    public static final Holder<Attribute> AGGRO_RANGE = register(
            "aggro_range",
            8.0,
            0.0,
            Double.MAX_VALUE,
            false
    );

    // initialize

    public static void initialize() {
    }

    // register
    private static Holder<Attribute> register(
            String name,
            double defaultValue,
            double minValue,
            double maxValue,
            boolean syncedWithClient
    ) {
        Identifier identifier = Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, name);

        Attribute attribute = new RangedAttribute(
                identifier.toLanguageKey(),
                defaultValue,
                minValue,
                maxValue
        ).setSyncable(syncedWithClient);

        return Registry.registerForHolder(BuiltInRegistries.ATTRIBUTE, identifier, attribute);
    }
    // :::register
}