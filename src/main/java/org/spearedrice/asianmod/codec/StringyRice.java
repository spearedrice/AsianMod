package org.spearedrice.asianmod.codec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class StringyRice implements Rice {
    public static final MapCodec<StringyRice> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.STRING.fieldOf("stringy_string").forGetter(StringyRice::getStringyString)
    ).apply(instance, StringyRice::new));

    private String stringyString;

    public StringyRice(String stringyString) {
        this.stringyString = stringyString;
    }

    public String getStringyString() {
        return stringyString;
    }

    @Override
    public RiceType<?> getType() {
        return RiceTypes.STRINGY_RICE;
    }
}