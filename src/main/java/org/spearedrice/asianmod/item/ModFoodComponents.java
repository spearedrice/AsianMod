package org.spearedrice.asianmod.item;

import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;

public class ModFoodComponents {

    public static final FoodComponent BOK_CHOY = new FoodComponent.Builder()
            .nutrition(3)
            .saturationModifier(0.25f)
            .build();

    public static final ConsumableComponent BOK_CHOY_CONSUMABLE = ConsumableComponent.builder()
            .consumeEffect(new ApplyEffectsConsumeEffect(
                    new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 200, 0),
                    0.15f
            ))
            .build();
// add boba soon (i'm lazy as fuck tho so i'm  gonna forget)
}