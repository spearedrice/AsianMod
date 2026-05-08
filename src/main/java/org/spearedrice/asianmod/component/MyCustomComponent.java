package org.spearedrice.asianmod.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record MyCustomComponent(float temperature, boolean burnt) {

    public static final Codec<MyCustomComponent> CODEC = RecordCodecBuilder.create(builder -> {
        return builder.group(
                Codec.FLOAT.fieldOf("temperature").forGetter(MyCustomComponent::temperature),
                Codec.BOOL.optionalFieldOf("burnt", false).forGetter(MyCustomComponent::burnt)
        ).apply(builder, MyCustomComponent::new);
    });
}