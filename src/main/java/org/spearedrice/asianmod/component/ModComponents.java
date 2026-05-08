package org.spearedrice.asianmod.component;

import com.mojang.serialization.Codec;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

import com.example.docs.ExampleMod;
import org.spearedrice.asianmod.AsianMod;

public class ModComponents {

    public static final DataComponentType<Integer> CLICK_COUNT_COMPONENT = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Identifier.fromNamespaceAndPath("asianmod", "click_count"),
            DataComponentType.<Integer>builder().persistent(Codec.INT).build()
    );

    public static final DataComponentType<ComponentWithTooltip> COMPONENT_WITH_TOOLTIP = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Identifier.fromNamespaceAndPath("asianmod", "click_count_with_tooltip"),
            DataComponentType.<ComponentWithTooltip>builder().persistent(ComponentWithTooltip.CODEC).build()
    );

    public static final DataComponentType<MyCustomComponent> MY_CUSTOM_COMPONENT = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Identifier.fromNamespaceAndPath("asianmod", "custom"),
            DataComponentType.<MyCustomComponent>builder().persistent(MyCustomComponent.CODEC).build()
    );

    protected static void initialize() {
        // check if this works
        AsianMod.LOGGER.info("Registering {} components", AsianMod.MOD_ID);
    }
}