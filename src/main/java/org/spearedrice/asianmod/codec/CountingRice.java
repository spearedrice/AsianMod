package org.spearedrice.asianmod.codec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class CountingRice implements Rice {
    public static final MapCodec<CountingRice> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("counting_number").forGetter(CountingRice::getCountingNumber)
    ).apply(instance, CountingRice::new));

    private int countingNumber;

    public CountingRice(int countingNumber) {
        this.countingNumber = countingNumber;
    }

    public int getCountingNumber() {
        return countingNumber;
    }

    @Override
    public RiceType<?> getType() {
        return RiceTypes.COUNTING_RICE;
    }
}