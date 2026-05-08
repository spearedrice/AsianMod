package org.spearedrice.asianmod.codec;

import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;

public class RiceTypes {
    public static final RiceType<StringyRice> STRINGY_RICE =
            register("stringy_rice", new RiceType<>(StringyRice.CODEC));

    public static final RiceType<CountingRice> COUNTING_RICE =
            register("counting_rice", new RiceType<>(CountingRice.CODEC));

    public static void register() { }

    public static <T extends Rice> RiceType<T> register(String id, RiceType<T> riceType) {
        return Registry.register(RiceType.REGISTRY, Identifier.fromNamespaceAndPath("asianmod", id), riceType);
    }
}