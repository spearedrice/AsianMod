package org.spearedrice.asianmod.codec;

import com.mojang.serialization.Lifecycle;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

public record RiceType<T extends Rice>(MapCodec<T> codec) {
    public static final Registry<RiceType<?>> REGISTRY = new MappedRegistry<>(
            ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath("asianmod", "rice_types")),
            Lifecycle.stable()
    );
}