package org.spearedrice.asianmod.datagen;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import net.minecraft.advancements.criterion.EntityFlagsPredicate;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.AllOf;
import net.minecraft.world.item.enchantment.effects.ApplyEntityImpulse;
import net.minecraft.world.item.enchantment.effects.PlaySoundEffect;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.phys.Vec3;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;

import org.spearedrice.asianmod.enchantment.ModEnchantments;
import org.spearedrice.asianmod.enchantment.effect.LightningEnchantmentEffect;

// :::provider
public class AsianModEnchantmentGenerator extends FabricDynamicRegistryProvider {

    public AsianModEnchantmentGenerator(
            FabricDataOutput output,
            CompletableFuture<HolderLookup.Provider> registriesFuture
    ) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.addAll(registries.lookupOrThrow(Registries.ENCHANTMENT));
    }

    @Override
    public String getName() {
        return "AsianMod Enchantments";
    }

    // :::bootstrap
    public static void bootstrap(BootstrapContext<Enchantment> context) {

        /*
         * THUNDERING
         */
        // register(
        //         context,
        //         ModEnchantments.THUNDERING,

        //         Enchantment.enchantment(
        //                         Enchantment.definition(
        //                                 context.lookup(Registries.ITEM)
        //                                         .getOrThrow(ItemTags.WEAPON_ENCHANTABLE),

        //                                 10,
        //                                 3,

        //                                 Enchantment.dynamicCost(1, 10),
        //                                 Enchantment.dynamicCost(1, 15),

        //                                 5,

        //                                 EquipmentSlotGroup.HAND
        //                         )
        //                 )

        //                 .withEffect(
        //                         EnchantmentEffectComponents.POST_ATTACK,
        //                         EnchantmentTarget.ATTACKER,
        //                         EnchantmentTarget.VICTIM,

        //                         new LightningEnchantmentEffect(
        //                                 LevelBasedValue.perLevel(0.4f, 0.2f)
        //                         )
        //                 )
        // );

        /*
         * REPULSION CURSE
         */
        // register(
        //         context,
        //         ModEnchantments.REPULSION_CURSE,

        //         Enchantment.enchantment(
        //                         Enchantment.definition(
        //                                 context.lookup(Registries.ITEM)
        //                                         .getOrThrow(ItemTags.WEAPON_ENCHANTABLE),

        //                                 10,
        //                                 3,

        //                                 Enchantment.dynamicCost(1, 10),
        //                                 Enchantment.dynamicCost(1, 15),

        //                                 5,

        //                                 EquipmentSlotGroup.HAND
        //                         )
        //                 )

        //                 .withEffect(
        //                         EnchantmentEffectComponents.POST_ATTACK,

        //                         EnchantmentTarget.ATTACKER,
        //                         EnchantmentTarget.ATTACKER,

        //                         AllOf.entityEffects(

        //                                 new ApplyEntityImpulse(
        //                                         new Vec3(0, 0.2, -1),
        //                                         new Vec3(1, 1, 1),
        //                                         LevelBasedValue.perLevel(0.7f, 0.2f)
        //                                 ),

        //                                 new PlaySoundEffect(
        //                                         List.of(SoundEvents.LUNGE_1),
        //                                         ConstantFloat.of(5),
        //                                         ConstantFloat.of(1)
        //                                 )
        //                         ),

        //                         LootItemEntityPropertyCondition.hasProperties(
        //                                 LootContext.EntityTarget.ATTACKER,

        //                                 EntityPredicate.Builder.entity().flags(
        //                                         EntityFlagsPredicate.Builder.flags()
        //                                                 .setIsFlying(false)
        //                                 )
        //                         )
        //                 )
        // );
    }
    // :::bootstrap

    // :::register-helper
    // private static void register(
    //         BootstrapContext<Enchantment> context,
    //         ResourceKey<Enchantment> key,
    //         Enchantment.Builder builder
    // ) {
    //     context.register(key, builder.build());
    // }
    // :::register-helper
}
// :::provider