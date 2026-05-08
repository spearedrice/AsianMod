package org.spearedrice.asianmod.codec;

import com.mojang.serialization.Codec;

public interface Rice {
    Codec<Rice> RICE_CODEC = RiceType.REGISTRY.byNameCodec()
            .dispatch("type", Rice::getType, RiceType::codec);

    RiceType<?> getType();
}