package org.spearedrice.asianmod.appearance;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.ARGB;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public record RainTintSource(int color) implements ItemTintSource {

    public static final MapCodec<RainTintSource> MAP_CODEC =
            RecordCodecBuilder.mapCodec(instance ->
                    instance.group(
                            ExtraCodecs.RGB_COLOR_CODEC
                                    .fieldOf("color")
                                    .forGetter(RainTintSource::color)
                    ).apply(instance, RainTintSource::new)
            );


    public RainTintSource() {
        this(0x00BFFF); // deepsky blue

    @Override
    public int calculate(ItemStack stack, ClientLevel level, LivingEntity entity) {

        if (level != null && level.isRaining()) {
            return ARGB.opaque(color);
        }


        return ARGB.opaque(0xFFEFD5); // idk
    }

    @Override
    public MapCodec<? extends ItemTintSource> type() {
        return MAP_CODEC;
    }
}